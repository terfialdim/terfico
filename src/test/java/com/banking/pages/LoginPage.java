package com.banking.pages;

public class LoginPage extends BaseMasterPage {

    public void navigateToBaseUrl(String url) {
        driver.get(url);
    }

    public void enterUsername(String username) {
        sendKeysToElement("usernameField", username);
    }

    public void enterPassword(String password) {
        sendKeysToElement("passwordField", password);
    }

    public void clickLoginButton() {
        clickElement("loginButton");
    }
}
