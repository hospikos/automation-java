package com.practicesoftwaretesting.api.user;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.api.user.models.LoginUserPayload;
import com.practicesoftwaretesting.api.user.models.RegisterUserPayload;
import com.practicesoftwaretesting.utils.ConfigReader;

public class UserSteps {
    static ConfigReader configReader = new ConfigReader();
    String adminEmail = configReader.getProperty("admin.email");
    String adminPassword = configReader.getProperty("admin.password");
    static String defaultPassword = configReader.getProperty("default.password");

    public static String registerNewUserViaApi(String email) {
        var userController = new UserController();
        var registerUserRequest = buildUser(email);
        return userController.registerUser(registerUserRequest)
                .as()
                .getId();
    }

    public void deleteUser(String userId) {
        var token = loginUser(adminEmail, adminPassword);
        new UserController().deleteUser(userId, token)
                .assertStatusCode(204);
    }

    public String loginUser(String userEmail, String password) {
        var userController = new UserController();
        var userLoginResponse = userController.loginUser(new LoginUserPayload(userEmail, password))
                .as();
        return userLoginResponse.getAccessToken();
    }

    public static RegisterUserPayload buildUser(String userEmail) {
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
                .email(userEmail)
                .build();
    }

    public static String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }

}
