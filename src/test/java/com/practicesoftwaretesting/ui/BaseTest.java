package com.practicesoftwaretesting.ui;

import com.codeborne.selenide.Configuration;
import com.practicesoftwaretesting.ui.pages.AccountPage;
import com.practicesoftwaretesting.ui.pages.HomePage;
import com.practicesoftwaretesting.ui.pages.LoginPage;
import com.practicesoftwaretesting.ui.pages.RegisterPage;
import com.practicesoftwaretesting.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    LoginPage loginPage = new LoginPage();
    RegisterPage registerPage = new RegisterPage();
    AccountPage accountPage = new AccountPage();
    HomePage homePage = new HomePage();
    static ConfigReader configReader = new ConfigReader();
    String adminEmail = configReader.getProperty("admin.email");
    String adminPassword = configReader.getProperty("admin.password");

    static {
        Configuration.baseUrl = configReader.getProperty("base.url");
        Configuration.timeout = Long.parseLong(configReader.getProperty("timeout"));
        Configuration.browserSize = configReader.getProperty("browser.size");
        Configuration.clickViaJs = Boolean.parseBoolean(configReader.getProperty("click.via.js"));
        Configuration.fastSetValue = Boolean.parseBoolean(configReader.getProperty("fast.set.value"));
        Configuration.headless = Boolean.parseBoolean(configReader.getProperty("headless"));
        Configuration.proxyEnabled = Boolean.parseBoolean(configReader.getProperty("proxy.enabled"));
    }

    public void registerAndLoginAsNewUser() {
        registerPage.open()
                .isLoaded()
                .assertThat()
                .hasCorrectInfo();

        var user = registerPage.getUser();
        registerPage.registerNewUser(user);

        loginPage.open()
                .isLoaded()
                .login(user.getEmail(), user.getPassword());

        accountPage.isLoaded();
        homePage.open();
    }

    public void loginAsAdmin() {
        loginPage.open()
                .isLoaded()
                .login(adminEmail, adminPassword);

        accountPage.isLoaded();
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
