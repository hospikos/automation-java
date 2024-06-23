package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.user.models.LoginUserPayload;
import com.practicesoftwaretesting.user.models.RegisterUserPayload;
import io.restassured.response.Response;

public class UserController extends BaseController {

    public Response registerUser(RegisterUserPayload registerUserPayload) {
        return baseClient()
                .body(registerUserPayload)
                .when()
                .post("/users/register");
    }

    public Response loginUser(LoginUserPayload loginUserPayload) {
        return baseClient()
                .body(loginUserPayload)
                .when()
                .post("/users/login");
    }

    public Response deleteUser(String userId, String token) {
        return baseClient()
                .header("Authorization", "Bearer " + token)
                .delete("/users/" + userId);
    }

}

