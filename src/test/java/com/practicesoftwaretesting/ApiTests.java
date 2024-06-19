package com.practicesoftwaretesting;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.practicesoftwaretesting.com")
                .log(LogDetail.ALL)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    @Test
    void testUser() {
        //Registration
        RegisterUserPayload userSignUp = RegisterUserPayload.builder()
                .firstName("Johne")
                .lastName("Lennone")
                .address("Street 1")
                .city("City")
                .state("State")
                .country("Country")
                .postcode("1234AA")
                .phone("0987654321")
                .dob("1941-01-01")
                .password("12Example#")
                .email("220john221@dou.example")
                .build();
        RegisterResponse registerResponse = given()
                .contentType(ContentType.JSON)
                .body(userSignUp)
                .when()
                .post("/users/register")
                .then()
                .statusCode(201)
                .extract()
                .as(RegisterResponse.class);

        //UserLogin
        LoginUserPayload userSignIn = LoginUserPayload.builder()
                .email("220john221@dou.example")
                .password("12Example#")
                .build();
        LoginResponse loginUserResponse = given()
                .contentType(ContentType.JSON)
                .body(userSignIn)
                .when()
                .post("/users/login")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);


        //AdminLogin
        LoginUserPayload adminSignIn = LoginUserPayload.builder()
                .email("admin@practicesoftwaretesting.com")
                .password("welcome01")
                .build();
        LoginResponse loginAdminResponse = given()
                .contentType(ContentType.JSON)
                .body(adminSignIn)
                .when()
                .post("/users/login")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        //Delete
        var userId = registerResponse.getId();
        var token = loginAdminResponse.getAccessToken();
        System.out.println(userId);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .delete("users/" + userId)
                .then()
                .statusCode(204);
    }
}
