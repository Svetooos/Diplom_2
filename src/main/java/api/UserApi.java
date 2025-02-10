package api;

import data.BaseResponse;
import data.user.CreateUserRequest;

public class UserApi extends BaseHttpClient {
    private final static String USER_REGISTER_PATH = "/api/auth/register";

    public BaseResponse create(CreateUserRequest createUserRequest) {
        return doPostRequest(USER_REGISTER_PATH, createUserRequest).as(BaseResponse.class);
    }
}
