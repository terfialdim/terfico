package com.banking.stepImp;

import com.banking.pages.LoginPage;
import com.banking.utils.DriverFactory;
import com.thoughtworks.gauge.Step;

import java.io.IOException;

public class LoginSteps extends DriverFactory {

    private final LoginPage loginPage = new LoginPage();

    public LoginSteps() throws IOException {
    }

    @Step("Navigate to base URL <url>")
    public void navigateToBaseUrl(String url) {
        loginPage.navigateToBaseUrl(url);
    }

    @Step("Enter <username> into the username field")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @Step("Enter <password> into the password field")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @Step("Click the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }
}
