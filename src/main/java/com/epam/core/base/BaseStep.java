package com.epam.core.base;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.webdriver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public abstract class BaseStep {
    private static final String HOME_PAGE_URL = "https://www.kinopoisk.ru/";
    private final WebDriver driver;

    public String getHomePageUrl(){
        return HOME_PAGE_URL;
    }

    public BaseStep() {
        WebDriverRunner.setWebDriver(WebDriverSingleton.getDriver());
        driver = getWebDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
