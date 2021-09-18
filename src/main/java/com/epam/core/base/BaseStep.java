package com.epam.core.base;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.webdriver.WebDriverSingleton;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.open;
import static com.epam.core.constant.KinopoiskConstants.HOME_PAGE_URL;

@Slf4j
public abstract class BaseStep {

    public void openHomePage() {
        WebDriverRunner.setWebDriver(WebDriverSingleton.getDriver());
        open(HOME_PAGE_URL);
        log.info("open home page '{}'", HOME_PAGE_URL);
    }
}
