package api;

import data.BaseResponse;
import data.user.CreateUserRequest;
import data.user.UserResponse;
import data.user.UpdateUserRequest;
import io.restassured.response.Response;

public class UserApi extends BaseHttpClient {
    private final static String USER_REGISTER_PATH = "/api/auth/register";
    private final static String USER_MODIFY_PATH = "/api/auth/user";

    public UserResponse create(CreateUserRequest createUserRequest) {
        Response response = doPostRequest(USER_REGISTER_PATH, createUserRequest);
        UserResponse userResponse = response.as(UserResponse.class);
        userResponse.setCode(response.getStatusCode());
        return userResponse;

    }

    public BaseResponse update(UpdateUserRequest updateUserRequest) {
        Response response = doPatchRequest(USER_MODIFY_PATH, updateUserRequest);
        UserResponse userResponse = response.as(UserResponse.class);
        userResponse.setCode(response.getStatusCode());
        return userResponse;
    }

    public UserResponse update(UpdateUserRequest updateUserRequest, String token) {
        Response response = doPatchRequest(USER_MODIFY_PATH, updateUserRequest, token);
        UserResponse userResponse = response.as(UserResponse.class);
        userResponse.setCode(response.getStatusCode());
        return userResponse;
    }

    public BaseResponse delete(String token) {
        return doDeleteRequest(USER_MODIFY_PATH, token).as(BaseResponse.class);
    }
}
