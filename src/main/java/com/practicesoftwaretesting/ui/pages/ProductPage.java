package com.practicesoftwaretesting.ui.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.practicesoftwaretesting.ui.utils.SelectorUtils.byDataTest;

public class ProductPage {

    private static final By PRODUCT_NAME = byDataTest("product-name");
    private static final By ADD_TO_CART_BUTTON = byDataTest("add-to-cart");

    public ProductPage isLoaded() {
        $(PRODUCT_NAME).shouldBe(visible);
        return this;
    }

    public ProductPage addToCart() {
        $(ADD_TO_CART_BUTTON).click();
        return this;
    }
}
