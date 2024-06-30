package com.practicesoftwaretesting.ui.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.ui.utils.SelectorUtils.byDataTest;

public class Header {

    public void clickSignInMenuItem() {
        $(byDataTest("nav-sign-in")).click();
    }
}
