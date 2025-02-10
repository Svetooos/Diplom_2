import api.UserApi;
import data.BaseResponse;
import data.user.CreateUserRequest;
import io.qameta.allure.Step;

import static org.junit.Assert.assertEquals;

public class UserSteps {

    private final UserApi userApi = new UserApi();

    @Step
    public void createUser_success(CreateUserRequest createUserRequest) {
        BaseResponse expected = new BaseResponse(true);
        BaseResponse actual = userApi.create(createUserRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
    }

    @Step
    public void createUser_duplicate(CreateUserRequest createUserRequest) {
        BaseResponse expected = new BaseResponse(false, "User already exists");
        userApi.create(createUserRequest);
        BaseResponse actual = userApi.create(createUserRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Step
    public void createUser_missData(CreateUserRequest createUserRequest) {
        BaseResponse expected = new BaseResponse(false, "Email, password and name are required fields");
        createUserRequest.setEmail(null);
        BaseResponse actual = userApi.create(createUserRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }
}
