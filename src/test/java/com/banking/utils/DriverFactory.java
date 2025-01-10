package com.banking.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory() {
        // Private constructor to enforce Singleton pattern
    }

    public static WebDriver getDriver(String browserName, boolean isHeadless) {
        if (driver == null) {
            initializeDriver(browserName, isHeadless);
        }
        return driver;
    }

    private static void initializeDriver(String browserName, boolean isHeadless) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(getChromeOptions(isHeadless));
                break;
            case "firefox":
                driver = new FirefoxDriver(getFirefoxOptions(isHeadless));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }

    private static ChromeOptions getChromeOptions(boolean isHeadless) {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications", "--start-maximized");

        if (isHeadless) {
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
        }
        // Config dosyasından ChromeDriver yolunu al
        String chromeDriverPath = Config.get("CHROME_DRIVER_PATH");
        System.setProperty("webdriver.chrome.driver", "C:/Users/TKA/IdeaProjects/forTitle-dosya/testiniumcase-yeni/src/test/resources/web_driver/chromedriver.exe");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions(boolean isHeadless) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");

        if (isHeadless) {
            options.addArguments("--headless");
        }
        return options;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriverWait getWait() {
        return wait;
    }
}