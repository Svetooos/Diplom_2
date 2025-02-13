package api;

import data.order.CreateOrderRequest;
import data.order.CreateOrderResponse;
import data.order.GetOrderResponse;
import io.restassured.response.Response;

public class OrderApi extends BaseHttpClient {
    static final String CREATE_ORDER_PATH = "/api/orders";
    static final String GET_ORDER_PATH = "/api/orders";

    public CreateOrderResponse create(CreateOrderRequest createOrderRequest) {
        Response response = doPostRequest(CREATE_ORDER_PATH, createOrderRequest);
        CreateOrderResponse createOrderResponse = response.as(CreateOrderResponse.class);
        createOrderResponse.setCode(response.getStatusCode());
        return createOrderResponse;
    }

    public CreateOrderResponse create(CreateOrderRequest createOrderRequest, String token) {
        Response response = doPostRequest(CREATE_ORDER_PATH, createOrderRequest, token);
        CreateOrderResponse createOrderResponse = response.as(CreateOrderResponse.class);
        createOrderResponse.setCode(response.getStatusCode());
        return createOrderResponse;
    }

    public GetOrderResponse get(String token) {
        Response response = doGetRequest(GET_ORDER_PATH, token);
        GetOrderResponse getOrderResponse = response.as(GetOrderResponse.class);
        getOrderResponse.setCode(response.getStatusCode());
        return getOrderResponse;
    }

    public GetOrderResponse get() {
        Response response = doGetRequest(GET_ORDER_PATH);
        GetOrderResponse getOrderResponse = response.as(GetOrderResponse.class);
        getOrderResponse.setCode(response.getStatusCode());
        return getOrderResponse;
    }
}