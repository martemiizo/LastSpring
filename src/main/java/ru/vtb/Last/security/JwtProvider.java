package ru.vtb.Last.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.vtb.Last.entity.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    private final String jwtAccessSecret;
    private final String jwtRefreshSecret;


    public JwtProvider(
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret
    ) {
        this.jwtAccessSecret = jwtAccessSecret;
        this.jwtRefreshSecret = jwtRefreshSecret;
    }

    public String generateAccessToken(@NonNull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtAccessSecret)
                .claim("role", user.getRole())
                .claim("firstName", user.getFirstname())
                .claim("lastName", user.getLastname())
                .compact();
    }

    public String generateRefreshToken(@NonNull User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(refreshExpiration)
                .signWith(SignatureAlgorithm.HS256, jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(@NonNull String token) {
        return validateToken(token, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NonNull String token) {
        return validateToken(token, jwtRefreshSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull String secret) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception expEx) {
            log.error("invalid token", expEx);
        }
        return false;
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, jwtRefreshSecret);
    }

    private Claims getClaims(@NonNull String token, @NonNull String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

}
