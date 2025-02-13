import data.login.LoginRequest;
import data.user.CreateUserRequest;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import step.LoginSteps;
import step.UserSteps;

public class LoginTest {
    private LoginSteps loginSteps = new LoginSteps();
    private UserSteps userSteps = new UserSteps();

    private CreateUserRequest createUserRequest = new CreateUserRequest(
            (RandomStringUtils.randomAlphabetic(10) + "@yandex.ru").toLowerCase(),
            RandomStringUtils.randomAlphabetic(8),
            RandomStringUtils.randomAlphabetic(8));
    private String token;


    @Before
    public void init() {
        token = userSteps.createUser_success(createUserRequest);
    }

    @After
    public void cleanUp() {
        if (token != null && !token.isEmpty()) {
            userSteps.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Успешная авторизация")
    public void login_success() {
        LoginRequest loginRequest = new LoginRequest(
                createUserRequest.getEmail(),
                createUserRequest.getPassword());
        loginSteps.login_success(loginRequest);
    }

    @Test
    @DisplayName("Неуспешная авторизация")
    public void login_unsuccess() {
        LoginRequest loginRequest = new LoginRequest(
                createUserRequest.getEmail(),
                createUserRequest.getPassword() + "1");
        loginSteps.login_unsuccessful(loginRequest);
    }
}