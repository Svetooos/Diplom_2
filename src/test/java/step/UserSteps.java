package step;

import api.UserApi;
import data.BaseResponse;
import data.user.CreateUserRequest;
import data.user.UpdateUserRequest;
import data.user.UserResponse;
import io.qameta.allure.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserSteps {

    private final UserApi userApi = new UserApi();

    @Step
    public void deleteUser(String token) {
        userApi.delete(token);
    }

    @Step
    public String createUser_success(CreateUserRequest createUserRequest) {
        UserResponse actual = userApi.create(createUserRequest);
        assertEquals(200, actual.getCode());
        assertEquals(true, actual.getSuccess());
        assertNotNull(actual.getUser());
        assertEquals(createUserRequest.getName(), actual.getUser().getName());
        assertEquals(createUserRequest.getEmail(), actual.getUser().getEmail());
        assertNotNull(actual.getAccessToken());
        return actual.getAccessToken();
    }

    @Step
    public String createUser_duplicate(CreateUserRequest createUserRequest) {
        BaseResponse expected = new BaseResponse(false, "User already exists");
        String token = userApi.create(createUserRequest).getAccessToken();
        BaseResponse actual = userApi.create(createUserRequest);
        assertEquals(403, actual.getCode());
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
        return token;
    }

    @Step
    public void createUser_missData(CreateUserRequest createUserRequest) {
        BaseResponse expected = new BaseResponse(false, "Email, password and name are required fields");
        createUserRequest.setEmail(null);
        BaseResponse actual = userApi.create(createUserRequest);
        assertEquals(403, actual.getCode());
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Step
    public void updateUser_unauthorized(UpdateUserRequest updateUserRequest) {
        BaseResponse expected = new BaseResponse(false, "You should be authorised");
        BaseResponse actual = userApi.update(updateUserRequest);
        assertEquals(401, actual.getCode());
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Step
    public void updateUser_authorized(UpdateUserRequest updateUserRequest, String token) {
        BaseResponse expected = new BaseResponse(true);
        UserResponse actual = userApi.update(updateUserRequest, token);
        assertEquals(200, actual.getCode());
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertNotNull(actual.getUser());
        assertEquals(updateUserRequest.getEmail(), actual.getUser().getEmail());
        assertEquals(updateUserRequest.getName(), actual.getUser().getName());
    }
}
