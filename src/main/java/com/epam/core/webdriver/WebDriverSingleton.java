package com.epam.core.webdriver;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
@Slf4j
@UtilityClass
public final class WebDriverSingleton {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public WebDriver getDriver(final Proxy proxy) {
        if (null == DRIVER.get()) {
            DRIVER.set(new WebDriverFactory(proxy).create().setupWebDriver());
        }
        return DRIVER.get();
    }

    public static void closeDriver() {
        if (null != DRIVER.get()) {
            try {
                DRIVER.get().quit();
                DRIVER.remove();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
