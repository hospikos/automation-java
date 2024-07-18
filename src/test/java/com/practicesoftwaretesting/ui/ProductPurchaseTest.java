package com.practicesoftwaretesting.ui;

import com.practicesoftwaretesting.api.user.UserSteps;
import com.practicesoftwaretesting.ui.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.practicesoftwaretesting.api.user.UserSteps.getUserEmail;

public class ProductPurchaseTest extends BaseTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    ProductPage productPage = new ProductPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    private String userId;

    @BeforeEach
    void setup() {
        var email = getUserEmail();
        userId = UserSteps.registerNewUserViaApi(email);
        loginAsUser(email, defaultPassword);
    }

    @Test
    void addProductToCartAndPurchaseIt() {
        homePage.isLoaded()
                .clickOnBoltCuttersItem();

        productPage.isLoaded()
                .addToCart();

        header.clickCartMenuItem();

        checkoutPage.isLoaded()
                .proceedToCheckout()
                .proceedToCheckoutSignedIn()
                .proceedToCheckoutBillingAddress()
                .chooseCashPaymentMethodAndConfirm()
                .assertThat()
                .successfulMessageIsDisplayed();
    }

    @AfterEach
    void cleanup() {
        deleteUser(userId);
    }
}
