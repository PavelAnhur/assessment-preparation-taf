package com.epam.core.configuration.webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.webdriver.WebDriverSingleton;

public class WebDriverConfigurator {
    private boolean isWebDriverControlledByUser;

    public void setUpWebDriver() {
        String virtualUrl = ConfigurationManager.configuration().selenideRemote();
        if (null != virtualUrl) {
            Configuration.proxyEnabled = true;
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
