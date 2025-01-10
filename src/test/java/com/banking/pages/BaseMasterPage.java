package com.banking.pages;

import com.banking.model.ElementInfo;
import com.banking.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseMasterPage {

    protected WebDriver driver = DriverFactory.getDriver();

    public By getElementInfoBy(ElementInfo elementInfo) {
        By by = null;
        switch (elementInfo.getType()) {
            case "css":
                by = By.cssSelector(elementInfo.getValue());
                break;
            case "xpath":
                by = By.xpath(elementInfo.getValue());
                break;
            case "id":
                by = By.id(elementInfo.getValue());
                break;
        }
        return by;
    }

    public WebElement findElement(String key) {
        ElementInfo elementInfo = DriverFactory.findElementInfoByKey(key); // Static çağrı
        By by = getElementInfoBy(elementInfo);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", element);
        return element;
    }

    public void clickElement(String key) {
        WebElement element = findElement(key);
        element.click();
    }

    public void sendKeysToElement(String key, String value) {
        WebElement element = findElement(key);
        element.clear();
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(String key) {
        return findElement(key).isDisplayed();
    }
}
