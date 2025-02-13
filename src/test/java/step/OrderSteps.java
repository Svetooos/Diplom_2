package step;

import api.IngredientApi;
import api.OrderApi;
import data.ingridient.GetIngredientsResponse;
import data.order.CreateOrderRequest;
import data.order.CreateOrderResponse;
import io.qameta.allure.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderSteps {

    private OrderApi orderApi = new OrderApi();
    private IngredientApi ingredientApi = new IngredientApi();

    @Step
    public void createOrder_unauthorized(CreateOrderRequest createOrderRequest) {
        CreateOrderResponse actual = orderApi.create(createOrderRequest);
        assertEquals(true, actual.getSuccess());
        assertNotNull(actual.getName());
        assertNotNull(actual.getOrder());
        assertNotNull(actual.getOrder().getNumber());
    }

    @Step
    public void createOrder_authorized(CreateOrderRequest createOrderRequest, String token) {
        CreateOrderResponse actual = orderApi.create(createOrderRequest, token);
        assertEquals(true, actual.getSuccess());
        assertNotNull(actual.getName());
        assertNotNull(actual.getOrder());
        assertNotNull(actual.getOrder().getNumber());
    }

    @Step
    public void createOrder_withIngredients(CreateOrderRequest createOrderRequest) {
        CreateOrderResponse actual = orderApi.create(createOrderRequest);
        assertEquals(true, actual.getSuccess());
        assertNotNull(actual.getName());
        assertNotNull(actual.getOrder());
        assertNotNull(actual.getOrder().getNumber());
    }

    @Step
    public void createOrder_incorrectIngredient(CreateOrderRequest createOrderRequest) {
        CreateOrderResponse actual = orderApi.create(createOrderRequest);
        assertEquals(false, actual.getSuccess());
        assertEquals("One or more ids provided are incorrect", actual.getMessage());
    }

    @Step
    public void createOrder_withoutIngredients(CreateOrderRequest createOrderRequest) {
        CreateOrderResponse actual = orderApi.create(createOrderRequest);
        assertEquals(false, actual.getSuccess());
        assertEquals("Ingredient ids must be provided", actual.getMessage());
    }

    @Step
    public GetIngredientsResponse getIngredients_success() {
        GetIngredientsResponse ingredients = ingredientApi.getIngredients();
        assertNotNull(ingredients);
        assertNotNull(ingredients.getData());
        return ingredients;
    }
}
