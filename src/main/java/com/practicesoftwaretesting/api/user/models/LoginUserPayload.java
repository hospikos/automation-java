package com.practicesoftwaretesting.api.user.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserPayload {
    private String email;
    private String password;
}

