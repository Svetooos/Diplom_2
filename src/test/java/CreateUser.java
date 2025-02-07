import data.user.User;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class CreateUser {
    private UserSteps userSteps = new UserSteps();
    private User user = new User(
            RandomStringUtils.randomAlphabetic(10) + "@yandex.ru",
            RandomStringUtils.randomAlphabetic(8),
            RandomStringUtils.randomAlphabetic(8));

    @Test
    @DisplayName("Успешное создание пользователя")
    public void createCourier_success() {
        userSteps.createUser_success(user);
    }

    @Test
    @DisplayName("Дубликат создания пользователя")
    public void createUser_duplicate() {
        userSteps.createUser_duplicate(user);
    }

    @Test
    @DisplayName("Дубликат создания пользователя")
    public void createUser_missData() {
        userSteps.createUser_missData(user);
    }
}
