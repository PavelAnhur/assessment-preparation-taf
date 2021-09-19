package com.epam.core.webdriver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public final class WebDriverSingleton {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == DRIVER.get()) {
            DRIVER.set(new WebDriverFactory().setupWebDriver());
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
