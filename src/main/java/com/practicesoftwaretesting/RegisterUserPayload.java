package com.practicesoftwaretesting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserPayload {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postcode;
    private String phone;
    private String dob;
    private String password;
    private String email;
}
