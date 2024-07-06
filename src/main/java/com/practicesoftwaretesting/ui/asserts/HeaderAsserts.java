package com.practicesoftwaretesting.ui.asserts;

import com.practicesoftwaretesting.ui.pages.Header;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class HeaderAsserts extends Header {

    public HeaderAsserts isSignedId(String fullUserName) {
        $(PROFILE_MENU).shouldHave(text(fullUserName));
        return this;
    }
}
