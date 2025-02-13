package data.login;

import data.BaseResponse;

public class LoginResponse extends BaseResponse {
    private String accessToken;
    private String refreshToken;
    private User user;

    public static class User {
        private String email;
        private String name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}