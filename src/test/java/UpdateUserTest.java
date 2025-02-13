import data.login.LoginRequest;
import data.user.CreateUserRequest;
import data.user.UpdateUserRequest;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import step.LoginSteps;
import step.UserSteps;

public class UpdateUserTest {
    private UserSteps userSteps = new UserSteps();
    private LoginSteps loginSteps = new LoginSteps();

    private CreateUserRequest createUserRequest = new CreateUserRequest(
            RandomStringUtils.randomAlphabetic(10) + "@yandex.ru",
            RandomStringUtils.randomAlphabetic(8),
            RandomStringUtils.randomAlphabetic(8));

    @Before
    public void init() {
        userSteps.createUser_success(createUserRequest);
    }

    @Test
    @DisplayName("Обновление пользователя без авторизации")
    public void updateUser_unauthorized() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail(createUserRequest.getEmail() + "abc");
        updateUserRequest.setPassword(createUserRequest.getPassword() + "abc");
        updateUserRequest.setName(createUserRequest.getName() + "abc");
        userSteps.updateUser_unauthorized(updateUserRequest);
    }


    @Test
    @DisplayName("Обновление пользователя с авторизацией")
    public void updateUser_authorized() {
        String email = createUserRequest.getEmail();
        String password = createUserRequest.getPassword();

        String token = loginSteps.login_success(new LoginRequest(email, password));

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail(email);
        updateUserRequest.setPassword(password);
        updateUserRequest.setName(createUserRequest.getName() + "abc");
        userSteps.updateUser_authorized(updateUserRequest, token);
    }
}
