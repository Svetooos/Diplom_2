package api;

import data.order.CreateOrderRequest;
import data.order.CreateOrderResponse;

public class OrderApi extends BaseHttpClient {
    static final String CREATE_ORDER_PATH = "/api/orders";
    static final String GET_ORDER_PATH = "/api/orders/all";

    public CreateOrderResponse create(CreateOrderRequest createOrderRequest) {
        return doPostRequest(CREATE_ORDER_PATH, createOrderRequest).as(CreateOrderResponse.class);
    }

    public CreateOrderResponse create(CreateOrderRequest createOrderRequest, String token) {
        return doPostRequest(CREATE_ORDER_PATH, createOrderRequest, token).as(CreateOrderResponse.class);
    }

}