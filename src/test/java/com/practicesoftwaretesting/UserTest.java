package com.practicesoftwaretesting;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.user.*;
import com.practicesoftwaretesting.user.assertion.LoginResponseAsserts;
import com.practicesoftwaretesting.user.assertion.RegisterUserResponseAsserts;
import com.practicesoftwaretesting.user.models.LoginResponse;
import com.practicesoftwaretesting.user.models.LoginUserPayload;
import com.practicesoftwaretesting.user.models.RegisterUserPayload;
import org.junit.jupiter.api.Test;

public class UserTest extends BaseTest {

    private String userEmail;
    private static final String DEFAULT_PASSWORD = "12Example#";
    UserController userController = new UserController();

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
                .password(DEFAULT_PASSWORD)
                .build();
        LoginResponse userLoginResponse = loginUser(loginUserPayload);
        new LoginResponseAsserts(userLoginResponse)
                .isNotExpired()
                .accessTokenIsNotNull()
                .tokenTypeIs("bearer");

        //AdminLogin
        LoginUserPayload adminSignIn = LoginUserPayload.builder()
                .email("admin@practicesoftwaretesting.com")
                .password("welcome01")
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
                .password(DEFAULT_PASSWORD)
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
