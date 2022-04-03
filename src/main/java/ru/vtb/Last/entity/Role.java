package ru.vtb.Last.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    RO_USER("USER"),
    RO_ADMIN("ADMIN");

    private final String vale;

    Role(String vale) {
        this.vale = vale;
    }

    @Override
    public String getAuthority() {
        return vale;
    }
}
