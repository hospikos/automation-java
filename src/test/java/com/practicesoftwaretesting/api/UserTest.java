package com.practicesoftwaretesting.api;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.api.user.UserController;
import com.practicesoftwaretesting.api.user.assertion.LoginResponseAsserts;
import com.practicesoftwaretesting.api.user.assertion.RegisterUserResponseAsserts;
import com.practicesoftwaretesting.api.user.models.LoginResponse;
import com.practicesoftwaretesting.api.user.models.LoginUserPayload;
import com.practicesoftwaretesting.api.user.models.RegisterUserPayload;
import com.practicesoftwaretesting.utils.ConfigReader;
import org.junit.jupiter.api.Test;

public class UserTest extends BaseTest {

    private String userEmail;
    UserController userController = new UserController();
    ConfigReader configReader = new ConfigReader();
    String adminEmail = configReader.getProperty("admin.email");
    String adminPassword = configReader.getProperty("admin.password");
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
        LoginUserPayload loginUserPayload = LoginUserPayload.builder()
                .email(userEmail)
                .password(defaultPassword)
                .build();
        LoginResponse userLoginResponse = loginUser(loginUserPayload);
        new LoginResponseAsserts(userLoginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");

        //AdminLogin
        LoginUserPayload adminSignIn = LoginUserPayload.builder()
                .email(adminEmail)
                .password(adminPassword)
                .build();
        LoginResponse loginAdminResponse = loginUser(adminSignIn);

        //Delete
        var userId = registerUserResponse.getId();
        var token = loginAdminResponse.getAccessToken();
        System.out.println(userId);
        userController.deleteUser(userId, token)
                .assertStatusCode(204);
    }

    private LoginResponse loginUser(LoginUserPayload loginUserPayload) {
        return userController.loginUser(loginUserPayload)
                .assertStatusCode(200)
                .as();
    }

    private RegisterUserPayload buildUser(String userEmail) {
        return RegisterUserPayload.builder()
                .firstName("Johne")
                .lastName("Lennone")
                .address("Street 1")
                .city("City")
                .state("State")
                .country("Country")
                .postcode("1234AA")
                .phone("0987654321")
                .dob("1941-01-01")
                .password(defaultPassword)
                .email(this.userEmail)
                .build();
    }

    private String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}
