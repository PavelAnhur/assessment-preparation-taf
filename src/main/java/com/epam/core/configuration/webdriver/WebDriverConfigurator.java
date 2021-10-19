package com.epam.core.configuration.webdriver;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.webdriver.WebDriverSingleton;

public class WebDriverConfigurator {
    private boolean isWebDriverControlledByUser;

    public void setUpWebDriver() {
        String virtualUrl = ConfigurationManager.configuration().selenideRemote();
        if (null != virtualUrl) {
            new RemoteWebDriverTuner().configRemoteWebDriver();
            isWebDriverControlledByUser = false;
        } else {
            WebDriverRunner.setWebDriver(WebDriverSingleton.getDriver());
            isWebDriverControlledByUser = true;
        }
    }

    public boolean isControlledByUser() {
        return isWebDriverControlledByUser;
    }
}
