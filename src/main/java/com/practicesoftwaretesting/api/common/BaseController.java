package com.practicesoftwaretesting.api.common;

import com.practicesoftwaretesting.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseController {

    private final ConfigReader configReader = new ConfigReader();

    protected RequestSpecification baseClient() {
        return RestAssured.given()
                .baseUri(configReader.getProperty("base.api.url"))
                .contentType(ContentType.JSON);
    }

}
