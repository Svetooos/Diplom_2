import data.login.LoginRequest;
import data.user.CreateUserRequest;
import data.user.UpdateUserRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import step.LoginSteps;
import step.UserSteps;

public class UpdateUserTest {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserTest.class);
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
    public void updateUser_unauthorized() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail(createUserRequest.getEmail());
        updateUserRequest.setPassword(createUserRequest.getPassword());
        updateUserRequest.setName(createUserRequest.getName() + "abc");
        userSteps.updateUser_unauthorized(updateUserRequest);
    }


    @Test
    public void updateUser_authorized() {
        String email = createUserRequest.getEmail();
        String password = createUserRequest.getPassword();
        String name = createUserRequest.getName();

        String token = loginSteps.login_success(new LoginRequest(email, password));
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(email, password, name);

        userSteps.updateUser_authorized(updateUserRequest, token);
    }
}
