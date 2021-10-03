package com.epam.core.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.config.PropertyDataReader;
import com.epam.core.webdriver.WebDriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

@Slf4j
public abstract class BaseTest {
    private boolean isWebDriverControlledByUser;

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        String virtualUrl = System.getProperty("selenide.remote");
        if (null != virtualUrl) {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.ANY);
            cap.setCapability("chrome.switches", List.of("--disable-notifications"));

            Configuration.browserCapabilities = cap;
            Configuration.browser = "chrome";
            Configuration.timeout = 10000;

            Configuration.remote = PropertyDataReader.getPropertyValue("virtualUrl");
            isWebDriverControlledByUser = false;
        } else {
            WebDriverRunner.setWebDriver(new WebDriverSingleton().getDriver());
            isWebDriverControlledByUser = true;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (isWebDriverControlledByUser) {
            WebDriverSingleton.closeDriver();
        } else {
            log.info("Web driver controlled by Selenide, no need to close it");
        }
    }
}
