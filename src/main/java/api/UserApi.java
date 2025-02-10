package api;

import data.BaseResponse;
import data.user.CreateUserRequest;
import data.user.UpdateUserRequest;

public class UserApi extends BaseHttpClient {
    private final static String USER_REGISTER_PATH = "/api/auth/register";
    private final static String USER_MODIFY_PATH = "/api/auth/user";

    public BaseResponse create(CreateUserRequest createUserRequest) {
        return doPostRequest(USER_REGISTER_PATH, createUserRequest).as(BaseResponse.class);
    }

    public BaseResponse update(UpdateUserRequest updateUserRequest) {
        return doPatchRequest(USER_MODIFY_PATH, updateUserRequest).as(BaseResponse.class);
    }

    public BaseResponse update(UpdateUserRequest updateUserRequest, String token) {
        return doPatchRequest(USER_MODIFY_PATH, updateUserRequest, token).as(BaseResponse.class);
    }

    public BaseResponse delete(String token) {
        return doDeleteRequest(USER_MODIFY_PATH, token).as(BaseResponse.class);
    }
}
