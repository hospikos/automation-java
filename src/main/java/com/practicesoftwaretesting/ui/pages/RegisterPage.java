package com.practicesoftwaretesting.ui.pages;

import com.github.javafaker.Faker;
import com.practicesoftwaretesting.api.user.models.RegisterUserPayload;
import com.practicesoftwaretesting.ui.asserts.RegisterPageAsserts;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.ui.utils.SelectorUtils.byDataTest;
import static com.practicesoftwaretesting.ui.utils.SelectorUtils.byFor;

public class RegisterPage {

    protected static final By FIRST_NAME_LABEL = byFor("first_name");
    protected static final By FIRST_NAME_INPUT = byId("first_name");
    protected static final By LAST_NAME_LABEL = byFor("last_name");
    protected static final By LAST_NAME_INPUT = byId("last_name");
    protected static final By DOB_LABEL = byFor("dob");
    protected static final By DOB_INPUT = byId("dob");
    protected static final By ADDRESS_LABEL = byFor("address");
    protected static final By ADDRESS_INPUT = byId("address");
    protected static final By POSTCODE_LABEL = byFor("postcode");
    protected static final By POSTCODE_INPUT = byId("postcode");
    protected static final By CITY_LABEL = byFor("city");
    protected static final By CITY_INPUT = byId("city");
    protected static final By STATE_LABEL = byFor("state");
    protected static final By STATE_INPUT = byId("state");
    protected static final By COUNTRY_LABEL = byFor("country");
    protected static final By COUNTRY_INPUT = byId("country");
    protected static final By PHONE_LABEL = byFor("phone");
    protected static final By PHONE_INPUT = byId("phone");
    protected static final By EMAIL_LABEL = byFor("email");
    protected static final By EMAIL_INPUT = byId("email");
    protected static final By PASSWORD_LABEL = byFor("password");
    protected static final By PASSWORD_INPUT = byId("password");
    protected static final By REGISTER_BUTTON = byDataTest("register-submit");

    public RegisterPage isLoaded() {
        $("h3").shouldHave(text("Customer registration"));
        return this;
    }

    public RegisterPageAsserts assertThat() {
        return new RegisterPageAsserts();
    }

    public void registerNewUser(RegisterUserPayload user) {
        $(FIRST_NAME_INPUT).setValue(user.getFirstName());
        $(LAST_NAME_INPUT).setValue(user.getLastName());
        $(DOB_INPUT).setValue(user.getDob());
        $(ADDRESS_INPUT).setValue(user.getAddress());
        $(CITY_INPUT).setValue(user.getCity());
        $(STATE_INPUT).setValue(user.getState());
        $(COUNTRY_INPUT).selectOption(user.getCountry());
        $(POSTCODE_INPUT).setValue(user.getPostcode());
        $(PHONE_INPUT).setValue(user.getPhone());
        $(EMAIL_INPUT).setValue(user.getEmail());
        $(PASSWORD_INPUT).setValue(user.getPassword());
        $(REGISTER_BUTTON).click();
    }

    public RegisterUserPayload getUser() {
        return RegisterUserPayload.builder()
                .firstName("George")
                .lastName("Harrison")
                .address("1243 Some Street")
                .city("Liverpool")
                .country("Uzbekistan")
                .state("Merseyside")
                .postcode("1234")
                .phone("123456677")
                .dob("01/01/1946")
                .email(this.getUserEmail())
                .password("12Example#")
                .build();
    }

    private String getUserEmail() {
        return Faker.instance()
                .friends()
                .character()
                .toLowerCase()
                .replaceAll(" ", "") + "@gmail.com";
    }
}
