package com.banking.stepImp;

import com.banking.pages.LoginPage;
import com.epam.reportportal.message.ReportPortalMessage;
import com.thoughtworks.gauge.Step;
import com.banking.utils.Config; // Config sınıfı import edildi.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class LoginSteps {
    private static final Logger LOGGER = LogManager.getLogger(Test.class);

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
        ReportPortalMessage message = new ReportPortalMessage(username);
        LOGGER.info(message);
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
