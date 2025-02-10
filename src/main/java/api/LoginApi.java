package api;

import data.login.LoginRequest;
import data.login.LoginResponse;

public class LoginApi extends BaseHttpClient {
    private final static String LOGIN_PATH = "/api/auth/login";

    public LoginResponse login(LoginRequest loginRequest) {
        return doPostRequest(LOGIN_PATH, loginRequest).as(LoginResponse.class);
    }
}