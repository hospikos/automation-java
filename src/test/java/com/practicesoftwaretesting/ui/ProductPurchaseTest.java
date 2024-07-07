package com.practicesoftwaretesting.ui;

import com.practicesoftwaretesting.ui.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class ProductPurchaseTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    AccountPage accountPage = new AccountPage();
    ProductPage productPage = new ProductPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @BeforeEach
    void setup() {
        open("https://practicesoftwaretesting.com/#/");
        homePage.isLoaded();
        header.clickSignInMenuItem();
        loginPage.isLoaded()
                .clickRegisterYourAccount();
        registerPage.isLoaded()
                .assertThat()
                .hasCorrectInfo();

        var user = registerPage.getUser();
        registerPage.registerNewUser(user);

        loginPage.isLoaded()
                .login(user.getEmail(), user.getPassword());

        accountPage.isLoaded();
        open("https://practicesoftwaretesting.com/#/");
    }

    @Test
    void addProductToCartAndPurchaseIt() {
        homePage.isLoaded()
                .clockOnBoltCuttersItem();

        productPage.isLoaded()
                .addToCart();

        header.clockCartMenuItem();

        checkoutPage.isLoaded()
                .proceedToCheckout()
                .proceedToCheckoutSignedIn()
                .proceedToCheckoutBillingAddress()
                .chooseCashPaymentMethodAndConfirm()
                .assertThat()
                .successfulMessageIsDisplayed();
    }
}
