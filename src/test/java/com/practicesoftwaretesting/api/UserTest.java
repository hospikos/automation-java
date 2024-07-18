package com.practicesoftwaretesting.api;

import com.practicesoftwaretesting.api.user.UserController;
import com.practicesoftwaretesting.api.user.assertion.LoginResponseAsserts;
import com.practicesoftwaretesting.api.user.assertion.RegisterUserResponseAsserts;
import com.practicesoftwaretesting.api.user.models.LoginResponse;
import com.practicesoftwaretesting.api.user.models.LoginUserPayload;
import com.practicesoftwaretesting.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.practicesoftwaretesting.api.user.UserSteps.buildUser;
import static com.practicesoftwaretesting.api.user.UserSteps.getUserEmail;

public class UserTest extends BaseTest {

    private String userEmail;
    private String userId;
    UserController userController = new UserController();
    ConfigReader configReader = new ConfigReader();
    String defaultPassword = configReader.getProperty("default.password");

    @Test
    void testUser() {
        userEmail = getUserEmail();

        //Registration
        var expectedUser = buildUser(userEmail);
        var registerUserResponse = userController.registerUser(expectedUser)
                .assertStatusCode(201)
                .as();
        new RegisterUserResponseAsserts(registerUserResponse)
                .createdAtIsNotNull()
                .firstNameIs(expectedUser.getFirstName())
                .lastNameIs(expectedUser.getLastName())
                .countryIs(expectedUser.getCountry())
                .phoneIs(expectedUser.getPhone())
                .cityIs(expectedUser.getCity())
                .addressIs(expectedUser.getAddress());

        //UserLogin
        LoginUserPayload loginUserPayload = new LoginUserPayload(userEmail, defaultPassword);
        LoginResponse userLoginResponse = loginUser(loginUserPayload);
        new LoginResponseAsserts(userLoginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");
        userId = registerUserResponse.getId();
    }

    private LoginResponse loginUser(LoginUserPayload loginUserPayload) {
        return userController.loginUser(loginUserPayload)
                .assertStatusCode(200)
                .as();
    }

    @AfterEach
    void cleanup() {
        var token = loginAsAdmin();
        userController.deleteUser(userId, token)
                .assertStatusCode(204);
    }

}
