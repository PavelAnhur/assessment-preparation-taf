package com.epam.tests;

import com.epam.core.webdriver.WebDriverSingleton;
import com.epam.core.webdriver.config.WebDriverConfigurator;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public abstract class BaseTest {
    private final WebDriverConfigurator configurator = new WebDriverConfigurator();

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        configurator.setUpWebDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (configurator.isControlledByUser()) {
            WebDriverSingleton.closeDriver();
        } else {
            log.info("Web driver controlled by Selenide, no need to close it");
        }
    }
}
