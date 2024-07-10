package com.practicesoftwaretesting.ui;

import com.practicesoftwaretesting.ui.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductPurchaseTest extends BaseTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    ProductPage productPage = new ProductPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @BeforeEach
    void setup() {
        registerAndLoginAsNewUser();
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
