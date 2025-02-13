import data.user.CreateUserRequest;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import step.UserSteps;

public class CreateUserTest {
    private UserSteps userSteps = new UserSteps();
    private CreateUserRequest createUserRequest = new CreateUserRequest(
            (RandomStringUtils.randomAlphabetic(10) + "@yandex.ru").toLowerCase(),
            RandomStringUtils.randomAlphabetic(8),
            RandomStringUtils.randomAlphabetic(8));
    private String token;

    @After
    public void cleanUp() {
        if (token != null && !token.isEmpty()) {
            userSteps.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Успешное создание пользователя")
    public void createCourier_success() {
        token = userSteps.createUser_success(createUserRequest);
    }

    @Test
    @DisplayName("Дубликат создания пользователя")
    public void createUser_duplicate() {
        token = userSteps.createUser_duplicate(createUserRequest);
    }

    @Test
    @DisplayName("Дубликат создания пользователя")
    public void createUser_missData() {
        userSteps.createUser_missData(createUserRequest);
    }
}
