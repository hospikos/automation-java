package com.practicesoftwaretesting.api.user.models;

import lombok.Data;

@Data
public class RegisterResponse {
    private String firstName;
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
    private String createdAt;
}
