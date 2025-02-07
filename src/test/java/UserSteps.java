import api.UserApi;
import data.BaseResponse;
import data.user.User;
import io.qameta.allure.Step;

import static org.junit.Assert.assertEquals;

public class UserSteps {

    private final UserApi userApi = new UserApi();

    @Step
    public void createUser_success(User user) {
        BaseResponse expected = new BaseResponse(true);
        BaseResponse actual = userApi.create(user);
        assertEquals(expected.getSuccess(), actual.getSuccess());
    }

    @Step
    public void createUser_duplicate(User user) {
        BaseResponse expected = new BaseResponse(false, "User already exists");
        userApi.create(user);
        BaseResponse actual = userApi.create(user);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Step
    public void createUser_missData(User user) {
        BaseResponse expected = new BaseResponse(false, "Email, password and name are required fields");
        user.setEmail(null);
        BaseResponse actual = userApi.create(user);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }
}
