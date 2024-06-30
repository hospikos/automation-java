package com.practicesoftwaretesting.api.user;

import com.practicesoftwaretesting.api.user.models.LoginResponse;
import com.practicesoftwaretesting.api.user.models.LoginUserPayload;
import com.practicesoftwaretesting.api.common.BaseController;
import com.practicesoftwaretesting.api.common.ResponseDecorator;
import com.practicesoftwaretesting.api.user.models.RegisterResponse;
import com.practicesoftwaretesting.api.user.models.RegisterUserPayload;

public class UserController extends BaseController {

    public ResponseDecorator<RegisterResponse> registerUser(RegisterUserPayload registerUserPayload) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(registerUserPayload)
                        .post("/users/register"),
                RegisterResponse.class
        );
    }

    public ResponseDecorator<LoginResponse> loginUser(LoginUserPayload loginUserPayload) {
        return new ResponseDecorator<>(
                baseClient()
                        .body(loginUserPayload)
                        .post("/users/login"),
                LoginResponse.class
        );
    }

    public ResponseDecorator<Void> deleteUser(String userId, String token) {
        return new ResponseDecorator<>(
                baseClient()
                        .header("Authorization", "Bearer " + token)
                        .delete("users/" + userId),
                Void.class
        );
    }
}

