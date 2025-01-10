package com.banking.stepImp;

import com.banking.pages.LoginPage;
import com.thoughtworks.gauge.Step;
import com.banking.utils.Config; // Config sınıfı import edildi.

public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Step("Navigate to baseURL")
    public void navigateToBaseURL() {
        String baseURL = Config.get("BASE_URL"); // Properties dosyasından alınır.
        loginPage.navigateToBaseURL(baseURL);
    }

    @Step("Enter username into the username field")
    public void enterUsername() {
        String username = Config.get("USERNAME"); // Properties dosyasından alınır.
        loginPage.enterUsername(username);
    }

    @Step("Enter password into the password field")
    public void enterPassword() {
        String password = Config.get("PASSWORD"); // Properties dosyasından alınır.
        loginPage.enterPassword(password);
    }

    @Step("Click the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }
}
