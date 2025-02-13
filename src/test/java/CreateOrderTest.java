import data.ingridient.GetIngredientsResponse;
import data.login.LoginRequest;
import data.order.CreateOrderRequest;
import data.user.CreateUserRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import step.LoginSteps;
import step.OrderSteps;
import step.UserSteps;

import java.util.Arrays;

public class CreateOrderTest {

    private OrderSteps orderSteps = new OrderSteps();
    private LoginSteps loginSteps = new LoginSteps();
    private UserSteps userSteps = new UserSteps();

    @Test
    public void createOrder_unauthorized() {
        GetIngredientsResponse ingredients = orderSteps.getIngredients_success();

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(Arrays.asList(ingredients.getData().get(0).get_id()));
        orderSteps.createOrder_unauthorized(createOrderRequest);
    }

    @Test
    public void createOrder_withIngredients() {
        GetIngredientsResponse ingredients = orderSteps.getIngredients_success();

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(Arrays.asList(
                ingredients.getData().get(0).get_id(),
                ingredients.getData().get(1).get_id(),
                ingredients.getData().get(2).get_id()));
        orderSteps.createOrder_withoutIngredients(createOrderRequest);
    }

    @Test
    public void createOrder_incorrectIngredient() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(Arrays.asList("61c0c5a71d1f82001bd0006d"));
        orderSteps.createOrder_incorrectIngredient(createOrderRequest);
    }

    @Test
    public void createOrder_withoutIngredients() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(null);
        orderSteps.createOrder_withoutIngredients(createOrderRequest);
    }

    @Test
    public void createOrder_authorized() {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                RandomStringUtils.randomAlphabetic(10) + "@yandex.ru",
                RandomStringUtils.randomAlphabetic(8),
                RandomStringUtils.randomAlphabetic(8));
        userSteps.createUser_success(createUserRequest);

        String token = loginSteps.login_success(new LoginRequest
                (createUserRequest.getEmail(), createUserRequest.getPassword()));

        GetIngredientsResponse ingredients = orderSteps.getIngredients_success();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(Arrays.asList(ingredients.getData().get(0).get_id()));
        orderSteps.createOrder_authorized(createOrderRequest, token);
    }

}
