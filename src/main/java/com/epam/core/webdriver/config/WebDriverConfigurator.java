package com.epam.core.webdriver.config;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.webdriver.WebDriverSingleton;

public class WebDriverConfigurator {
    private boolean isWebDriverControlledByUser;

    public void setUpWebDriver() {
        String virtualUrl = System.getProperty("selenide.remote");
        if (null != virtualUrl) {
            new RemoteWebDriverTuner().configRemoteWebDriver();
            isWebDriverControlledByUser = false;
        } else {
            WebDriverRunner.setWebDriver(WebDriverSingleton.getDriver(null));
            isWebDriverControlledByUser = true;
        }
    }

    public boolean isControlledByUser() {
        return isWebDriverControlledByUser;
    }
}
