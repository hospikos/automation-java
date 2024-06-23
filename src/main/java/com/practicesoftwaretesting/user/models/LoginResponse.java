package com.practicesoftwaretesting.user.models;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String tokenType;
    private int expiresIn;
}

