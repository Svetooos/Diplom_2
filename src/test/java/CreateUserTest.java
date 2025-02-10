import data.user.CreateUserRequest;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class CreateUserTest {
    private UserSteps userSteps = new UserSteps();
    private CreateUserRequest createUserRequest = new CreateUserRequest(
            RandomStringUtils.randomAlphabetic(10) + "@yandex.ru",
            RandomStringUtils.randomAlphabetic(8),
            RandomStringUtils.randomAlphabetic(8));

    @Test
    @DisplayName("Успешное создание пользователя")
    public void createCourier_success() {
        userSteps.createUser_success(createUserRequest);
    }

    @Test
    @DisplayName("Дубликат создания пользователя")
    public void createUser_duplicate() {
        userSteps.createUser_duplicate(createUserRequest);
    }

    @Test
    @DisplayName("Дубликат создания пользователя")
    public void createUser_missData() {
        userSteps.createUser_missData(createUserRequest);
    }
}
