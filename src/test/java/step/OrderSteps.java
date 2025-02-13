package step;

import api.IngredientApi;
import api.OrderApi;
import data.BaseResponse;
import data.ingridient.GetIngredientsResponse;
import data.order.CreateOrderRequest;
import data.order.CreateOrderResponse;
import data.order.GetOrderResponse;
import io.qameta.allure.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderSteps {

    private OrderApi orderApi = new OrderApi();
    private IngredientApi ingredientApi = new IngredientApi();

    @Step
    public void createOrder_unauthorized(CreateOrderRequest createOrderRequest) {
        BaseResponse expected = new BaseResponse(true);
        CreateOrderResponse actual = orderApi.create(createOrderRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertNotNull(actual.getName());
        assertNotNull(actual.getOrder());
        assertNotNull(actual.getOrder().getNumber());
    }

    @Step
    public void createOrder_authorized(CreateOrderRequest createOrderRequest, String token) {
        BaseResponse expected = new BaseResponse(true);
        CreateOrderResponse actual = orderApi.create(createOrderRequest, token);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertNotNull(actual.getName());
        assertNotNull(actual.getOrder());
        assertNotNull(actual.getOrder().getNumber());
    }

    @Step
    public void createOrder_withIngredients(CreateOrderRequest createOrderRequest) {
        BaseResponse expected = new BaseResponse(true);
        CreateOrderResponse actual = orderApi.create(createOrderRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertNotNull(actual.getName());
        assertNotNull(actual.getOrder());
        assertNotNull(actual.getOrder().getNumber());
    }

    @Step
    public void createOrder_incorrectIngredient(CreateOrderRequest createOrderRequest) {
        BaseResponse expected = new BaseResponse(false, "One or more ids provided are incorrect");
        CreateOrderResponse actual = orderApi.create(createOrderRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Step
    public void createOrder_withoutIngredients(CreateOrderRequest createOrderRequest) {
        BaseResponse expected = new BaseResponse(false, "Ingredient ids must be provided");
        CreateOrderResponse actual = orderApi.create(createOrderRequest);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Step
    public GetIngredientsResponse getIngredients_success() {
        GetIngredientsResponse ingredients = ingredientApi.getIngredients();
        assertNotNull(ingredients);
        assertNotNull(ingredients.getData());
        return ingredients;
    }

    @Step
    public void getOrder_unauthorized() {
        BaseResponse expected = new BaseResponse(false, "You should be authorised");
        GetOrderResponse actual = orderApi.get();
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @Step
    public void getOrder_authorized(String token) {
        BaseResponse expected = new BaseResponse(true);
        GetOrderResponse actual = orderApi.get(token);
        assertEquals(expected.getSuccess(), actual.getSuccess());
        assertNotNull(actual.getOrders());
        assertNotNull(actual.getOrders().get(0));
    }
}
