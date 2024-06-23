package com.practicesoftwaretesting;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.*;
import com.practicesoftwaretesting.user.models.LoginResponse;
import com.practicesoftwaretesting.user.models.LoginUserPayload;
import com.practicesoftwaretesting.user.models.RegisterResponse;
import com.practicesoftwaretesting.user.models.RegisterUserPayload;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest extends BaseTest{

    private String userEmail;
    private static final String USER_PASSWORD = "12Example#";
    UserController userController = new UserController();

    @Test
    void testUser() {

        userEmail = getUserEmail();
        //Registration
        RegisterUserPayload registerUserPayload = buildUser();
        RegisterResponse registerResponse = userController.registerUser(registerUserPayload)
                .then()
                .statusCode(201)
                .extract()
                .as(RegisterResponse.class);
        assertNotNull(registerResponse.getId());

        //UserLogin
        LoginUserPayload loginUserPayload = LoginUserPayload.builder()
                .email(userEmail)
                .password("12Example#")
                .build();
        LoginResponse loginUserResponse = loginUser(loginUserPayload);
        assertNotNull(loginUserResponse.getAccessToken());

        //AdminLogin
        LoginUserPayload adminSignIn = LoginUserPayload.builder()
                .email("admin@practicesoftwaretesting.com")
                .password("welcome01")
                .build();
        LoginResponse loginAdminResponse = loginUser(adminSignIn);

        //Delete
        var userId = registerResponse.getId();
        var token = loginAdminResponse.getAccessToken();
        System.out.println(userId);
        userController.deleteUser(userId, token)
                .then()
                .statusCode(204);
    }

    private LoginResponse loginUser(LoginUserPayload loginUserPayload) {
        return userController.loginUser(loginUserPayload)
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);
    }

    private RegisterUserPayload buildUser() {
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
                .password(USER_PASSWORD)
                .email(userEmail)
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
