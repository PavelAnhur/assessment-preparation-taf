package com.epam.core.base;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.webdriver.WebDriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Slf4j
public abstract class BaseStep {
    private static final String HOME_PAGE_URL = "https://www.kinopoisk.ru/";
    private final WebDriver driver;

    public BaseStep() {
        WebDriverRunner.setWebDriver(WebDriverSingleton.getDriver());
        driver = getWebDriver();
    }

    public void openHomePage() {
        open(HOME_PAGE_URL);
        log.info("open home page '{}'", HOME_PAGE_URL);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
