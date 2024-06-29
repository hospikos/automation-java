package com.practicesoftwaretesting.user;

import com.practicesoftwaretesting.common.BaseController;
import com.practicesoftwaretesting.common.ResponseDecorator;
import com.practicesoftwaretesting.user.models.LoginResponse;
import com.practicesoftwaretesting.user.models.LoginUserPayload;
import com.practicesoftwaretesting.user.models.RegisterResponse;
import com.practicesoftwaretesting.user.models.RegisterUserPayload;

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

