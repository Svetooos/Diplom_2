package api;

import data.BaseResponse;
import data.user.User;

public class UserApi extends BaseHttpClient {
    private final static String USER_REGISTER_PATH = "/api/auth/register";

    public BaseResponse create(User user) {
        return doPostRequest(USER_REGISTER_PATH, user).as(BaseResponse.class);
    }
}
