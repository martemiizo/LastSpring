package ru.vtb.Last.services;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.vtb.Last.entity.User;
import ru.vtb.Last.repository.UserRepository;
import ru.vtb.Last.security.JwtProvider;
import ru.vtb.Last.security.JwtRequest;
import ru.vtb.Last.security.JwtResponse;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private static final String USER_NOT_FOUND = "Пользователь не найден";
    private static final String PASS_NOT_VALID = "Неправильный пароль";
    private static final String TOKEN_NOT_VALID = "Неверный JWT токен";
    private static final String REFRESH = "refresh";
    private static final String ACCESSTOKEN = "accesstoken";


    private final UserRepository userRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userRepository.getByLogin(authRequest.getLogin());
        if (user == null) {
            log.error(USER_NOT_FOUND);
            return null;
        }

        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            log.error(PASS_NOT_VALID);
            return null;
        }
    }

    public JwtResponse getRefreshToken(@NonNull String refreshToken, String typeOper) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.getByLogin(login);
                if (user == null) {
                    log.error(USER_NOT_FOUND);
                    return null;
                }

                final String accessToken = jwtProvider.generateAccessToken(user);
                if (!typeOper.equals(REFRESH)) {
                    return new JwtResponse(accessToken, null);
                } else {
                    final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                    refreshStorage.put(user.getLogin(), newRefreshToken);
                    return new JwtResponse(accessToken, newRefreshToken);
                }
            }
        }
        if (!typeOper.equals(REFRESH)) {
            return new JwtResponse(null, null);
        } else {
            throw new AuthException(TOKEN_NOT_VALID);
        }
    }

    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        return getRefreshToken(refreshToken, ACCESSTOKEN);
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        return getRefreshToken(refreshToken, REFRESH);
    }


}
