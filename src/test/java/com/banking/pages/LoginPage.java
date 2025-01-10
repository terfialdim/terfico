package com.banking.pages;

import com.banking.utils.DriverFactory;
import com.banking.utils.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private final ElementHelper elementHelper;
    private final WebDriver driver;

    public LoginPage() {
        this.driver = DriverFactory.getDriver("chrome", false); // DriverFactory'den driver alınıyor
        this.elementHelper = new ElementHelper(driver);

        // JSON dosyasından elementleri yükle
        elementHelper.loadElementsFromJson("src/test/resources/elementValues/login.json");
    }

    public void navigateToBaseURL(String baseURL) {
        driver.get(baseURL); // Doğrudan driver üzerinden çalışıyor
    }

    public void enterUsername(String username) {
        WebElement usernameField = elementHelper.findElement("usernameField");
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordField = elementHelper.findElement("passwordField");
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = elementHelper.findElement("loginButton");
        loginButton.click();
    }
}
