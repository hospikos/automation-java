package com.practicesoftwaretesting.ui.pages;

import com.practicesoftwaretesting.ui.asserts.RegisterPageAsserts;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {

    public RegisterPage isLoaded() {
        $("h3").shouldHave(text("Customer registration"));
        return this;
    }

    public RegisterPageAsserts assertThat() {
        return new RegisterPageAsserts();
    }

}
