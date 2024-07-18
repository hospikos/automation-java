package com.practicesoftwaretesting.ui.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practicesoftwaretesting.ui.utils.SelectorUtils.byDataTest;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class HomePage {

    private static final By BANNER = className("img-fluid");
    private static final By PRODUCT_CARDS = byDataTest("product-name");
    public static final By BOLT_CUTTERS_ITEM = xpath("//h5[@data-test='product-name' and text()=' Bolt Cutters ']");

    public HomePage isLoaded() {
        $(BANNER).shouldBe(visible);
        return this;
    }

    public void clickOnTheFirstProduct() {
        $$(PRODUCT_CARDS).first().click();
    }

    public void clickOnBoltCuttersItem() {
        $(BOLT_CUTTERS_ITEM).click();
    }

    public HomePage open() {
        Selenide.open("/");
        return this;
    }
}
