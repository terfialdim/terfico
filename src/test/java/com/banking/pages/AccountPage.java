package com.banking.pages;

import com.banking.utils.DriverFactory;
import com.banking.utils.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {

    private final ElementHelper elementHelper;
    private final WebDriver driver;

    public AccountPage() {
        this.driver = DriverFactory.getDriver("chrome", false); // DriverFactory'den driver alınıyor
        this.elementHelper = new ElementHelper(driver);

        // JSON dosyasından elementleri yükle
        elementHelper.loadElementsFromJson("src/test/resources/elementValues/account.json");
    }

    public void clickOpenMoneyTransferButton() {
        WebElement button = elementHelper.findElement("openMoneyTransferButton");
        button.click();
    }

    public boolean isElementVisible(String key) {
        try {
            WebElement element = elementHelper.findElement(key);
            return element.isDisplayed();
        } catch (Exception e) {
            return false; // Element bulunamazsa veya görünür değilse false döner
        }
    }

}
