package com.epam.core.step;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.webdriver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseStep implements IBaseStep {
    private final WebDriver driver;

    public BaseStep() {
        WebDriverRunner.setWebDriver(WebDriverSingleton.getDriver());
        driver = getWebDriver();
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void close() {
        WebDriverSingleton.closeDriver();
    }
}
