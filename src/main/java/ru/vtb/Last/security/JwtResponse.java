package ru.vtb.Last.security;

public class JwtResponse {

    private static final String BEARER = "Bearer";
    private String accessToken;
    private String refreshToken;

    public JwtResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return BEARER;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
