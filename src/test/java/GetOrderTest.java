import data.ingridient.GetIngredientsResponse;
import data.login.LoginRequest;
import data.order.CreateOrderRequest;
import data.user.CreateUserRequest;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import step.LoginSteps;
import step.OrderSteps;
import step.UserSteps;

import java.util.Arrays;

public class GetOrderTest {
    private OrderSteps orderSteps = new OrderSteps();
    private UserSteps userSteps = new UserSteps();
    private LoginSteps loginSteps = new LoginSteps();
    private String token;

    @After
    public void cleanUp() {
        if (token != null && !token.isEmpty()) {
            userSteps.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Получение заказов без авторизации")
    public void getOrder_unauthorized() {
        orderSteps.getOrder_unauthorized();
    }

    @Test
    @DisplayName("Получение заказов c авторизацией")
    public void getOrder_authorized() {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                (RandomStringUtils.randomAlphabetic(10) + "@yandex.ru").toLowerCase(),
                RandomStringUtils.randomAlphabetic(8),
                RandomStringUtils.randomAlphabetic(8));
        userSteps.createUser_success(createUserRequest);

        token = loginSteps.login_success(new LoginRequest
                (createUserRequest.getEmail(), createUserRequest.getPassword()));

        GetIngredientsResponse ingredients = orderSteps.getIngredients_success();
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(Arrays.asList(ingredients.getData().get(0).get_id()));
        orderSteps.createOrder_authorized(createOrderRequest, token);
        orderSteps.getOrder_authorized(token);
    }
}
