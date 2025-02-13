package step;

import api.LoginApi;
import data.BaseResponse;
import data.login.LoginRequest;
import data.login.LoginResponse;
import io.qameta.allure.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginSteps {
    private final LoginApi loginApi = new LoginApi();

    @Step
    public String login_success(LoginRequest loginRequest) {
        BaseResponse expected = new BaseResponse(true);
        LoginResponse actual = loginApi.login(loginRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertNotNull(actual.getAccessToken());
        assertNotNull(actual.getRefreshToken());
        assertNotNull(actual.getUser());
        return actual.getAccessToken();
    }

    @Step
    public void login_unsuccessful(LoginRequest loginRequest) {
        BaseResponse expected = new BaseResponse(false, "email or password are incorrect");
        LoginResponse actual = loginApi.login(loginRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }
}