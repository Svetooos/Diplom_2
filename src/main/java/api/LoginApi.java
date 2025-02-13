package api;

import data.login.LoginRequest;
import data.login.LoginResponse;
import io.restassured.response.Response;

public class LoginApi extends BaseHttpClient {
    private final static String LOGIN_PATH = "/api/auth/login";

    public LoginResponse login(LoginRequest loginRequest) {
        Response response = doPostRequest(LOGIN_PATH, loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class);
        loginResponse.setCode(response.getStatusCode());
        return loginResponse;
    }
}