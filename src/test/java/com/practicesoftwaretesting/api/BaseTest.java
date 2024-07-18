package com.practicesoftwaretesting.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.practicesoftwaretesting.api.user.UserSteps;
import com.practicesoftwaretesting.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public abstract class BaseTest {

    static ConfigReader configReader = new ConfigReader();
    UserSteps userSteps = new UserSteps();
    String adminEmail = configReader.getProperty("admin.email");
    String adminPassword = configReader.getProperty("admin.password");

    static {
        configureRestAssured();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    private static void configureRestAssured() {
        var objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(
                        RestAssured.config()
                                .getObjectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) -> objectMapper));
    }

    public String loginUser(String userEmail, String password) {
        return userSteps.loginUser(userEmail, password);
    }

    public String loginAsAdmin() {
        return loginUser(adminEmail, adminPassword);
    }
}
