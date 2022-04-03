package ru.vtb.Last.security;

import io.jsonwebtoken.Claims;
import ru.vtb.Last.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUtils {

    private JwtUtils() {
    }

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setFirstName(claims.get("lastName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        String role = claims.get("role", String.class);
        final List<String> roles = new ArrayList<>();
        roles.add(role);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

}
