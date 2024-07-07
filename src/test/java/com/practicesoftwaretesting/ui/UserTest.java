package com.practicesoftwaretesting.ui;

import com.practicesoftwaretesting.ui.pages.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class UserTest {

    HomePage homePage = new HomePage();
    Header header = new Header();
    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    AccountPage accountPage = new AccountPage();

    @Test
    void registerNewUserAndLogin() {
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
        header.assertThat().isSignedId(user.getFirstName() + " " + user.getLastName());
    }

}
