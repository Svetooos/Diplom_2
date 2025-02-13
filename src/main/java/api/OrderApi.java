package api;

import data.BaseResponse;

public class OrderApi extends BaseHttpClient {
    static final String CREATE_ORDER_PATH = "/api/orders";
    static final String GET_ORDER_PATH = "/api/orders/all";

    public BaseResponse createOrderWithoutAuthorization(String requestBody) {
        return doPostRequest(CREATE_ORDER_PATH, requestBody);
    }

    public BaseResponse createOrderAuthorization(String userToken, String requestBody) {
        return doPostRequest(CREATE_ORDER_PATH, userToken, requestBody);
    }

    public BaseResponse takeAllOrdersWithoutAuthorization() {
        return doGetRequest(GET_ORDER_PATH);
    }

    public BaseResponse takeOrdersAuthorization(String userToken) {
        return doGetRequest(CREATE_ORDER_PATH, userToken);
    }
}