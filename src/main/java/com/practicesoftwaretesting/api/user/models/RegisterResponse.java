package com.practicesoftwaretesting.api.user.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterResponse {
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
    private String email;
    private String id;
    @JsonProperty("created_at")
    private String createdAt;
}
