package com.practicesoftwaretesting.api.user.assertion;

import com.practicesoftwaretesting.api.user.models.LoginResponse;
import lombok.AllArgsConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class LoginResponseAsserts {

    private LoginResponse loginResponse;

    public LoginResponseAsserts accessTokenIsNotNull() {
        assertThat(loginResponse.getAccessToken())
                .withFailMessage("accessToken is null")
                .isNotNull();
        return this;
    }

    public LoginResponseAsserts tokenTypeIs(String expectedTokenType) {
        assertThat(loginResponse.getTokenType())
                .withFailMessage(String.format(
                        "tokenType should be %s but was %s",
                        expectedTokenType,
                        loginResponse.getTokenType()
                ))
                .isEqualTo(expectedTokenType);
        return this;
    }

    public LoginResponseAsserts isNotExpired() {
        assertThat(loginResponse.getExpiresIn())
                .withFailMessage("expiresIn should be greater than 0")
                .isGreaterThan(0);
        return this;
    }
}
