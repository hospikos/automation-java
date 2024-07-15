package com.practicesoftwaretesting.api.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserPayload {
    private String email;
    private String password;
}

