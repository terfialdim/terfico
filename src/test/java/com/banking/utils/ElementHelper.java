package com.banking.utils;

import com.banking.model.ElementInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ElementHelper {

    private static final Logger logger = LoggerFactory.getLogger(ElementHelper.class);
    private final WebDriver driver;
    private final Map<String, ElementInfo> elementMap = new ConcurrentHashMap<>();

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void loadElementsFromJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            Type elementType = new TypeToken<List<ElementInfo>>() {}.getType();
            List<ElementInfo> elements = gson.fromJson(reader, elementType);
            for (ElementInfo element : elements) {
                elementMap.put(element.getKey(), element);
                logger.info("Loaded element: {} with locator: {}", element.getKey(), element.getValue());
            }
        } catch (IOException e) {
            logger.error("Error loading elements from JSON file: {}", filePath, e);
            throw new RuntimeException("Failed to load elements from JSON file", e);
        }
    }

    public WebElement findElement(String key) {
        ElementInfo elementInfo = elementMap.get(key);
        if (elementInfo == null) {
            logger.error("Element not found in the map for key: {}", key);
            throw new IllegalArgumentException("Element not found for key: " + key);
        }

        By locator = getBy(elementInfo);
        try {
            WebElement element = driver.findElement(locator);
            logger.info("Element found: {}", key);
            return element;
        } catch (Exception e) {
            logger.error("Unable to find element for key: {}. Locator: {}", key, locator, e);
            throw e;
        }
    }

    private By getBy(ElementInfo elementInfo) {
        switch (elementInfo.getType().toLowerCase()) {
            case "css":
                return By.cssSelector(elementInfo.getValue());
            case "xpath":
                return By.xpath(elementInfo.getValue());
            case "id":
                return By.id(elementInfo.getValue());
            default:
                logger.error("Invalid locator type: {}", elementInfo.getType());
                throw new IllegalArgumentException("Invalid locator type: " + elementInfo.getType());
        }
    }
}
