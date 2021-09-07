package com.epam.core.webdriver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public final class WebDriverSingleton {
    private static final ThreadLocal<WebDriver> DRIVERS = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == DRIVERS.get()) {
            DRIVERS.set(new WebDriverFactory().setupWebDriver());
        }
        return DRIVERS.get();
    }

    public static void closeDriver() {
        if (null != DRIVERS.get()) {
            try {
                DRIVERS.get().quit();
                DRIVERS.remove();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}

