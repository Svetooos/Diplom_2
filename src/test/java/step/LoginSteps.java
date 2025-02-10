package step;

import api.LoginApi;
import data.login.LoginRequest;
import data.login.LoginResponse;
import io.qameta.allure.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginSteps {
    private final LoginApi loginApi = new LoginApi();

    @Step
    public String login_success(LoginRequest loginRequest) {
        LoginResponse actual = loginApi.login(loginRequest);
        assertEquals(true, actual.getSuccess());
        assertNotNull(actual.getAccessToken());
        assertNotNull(actual.getRefreshToken());
        assertNotNull(actual.getUser());
        return actual.getAccessToken();
    }

    @Step
    public void login_unsuccessful(LoginRequest loginRequest) {
        LoginResponse actual = loginApi.login(loginRequest);
        assertEquals(false, actual.getSuccess());
        assertEquals("email or password are incorrect", actual.getMessage());
    }
}