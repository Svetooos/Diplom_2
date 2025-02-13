package api;

import data.order.CreateOrderRequest;
import data.order.CreateOrderResponse;
import data.order.GetOrderResponse;

public class OrderApi extends BaseHttpClient {
    static final String CREATE_ORDER_PATH = "/api/orders";
    static final String GET_ORDER_PATH = "/api/orders";

    public CreateOrderResponse create(CreateOrderRequest createOrderRequest) {
        return doPostRequest(CREATE_ORDER_PATH, createOrderRequest).as(CreateOrderResponse.class);
    }

    public CreateOrderResponse create(CreateOrderRequest createOrderRequest, String token) {
        return doPostRequest(CREATE_ORDER_PATH, createOrderRequest, token).as(CreateOrderResponse.class);
    }

    public GetOrderResponse get(String token) {
        return doGetRequest(GET_ORDER_PATH, token).as(GetOrderResponse.class);
    }

    public GetOrderResponse get() {
        return doGetRequest(GET_ORDER_PATH).as(GetOrderResponse.class);
    }
}