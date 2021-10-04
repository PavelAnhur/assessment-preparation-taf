package com.epam.core.webdriver;

import com.epam.core.util.reflection.ClassScanner;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public final class WebDriverSingleton {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private Proxy proxy;

    public WebDriver getDriver() {
        if (null == DRIVER.get()) {
            if (ClassScanner.isAnnotatedWithProxy()) {
                DRIVER.set(new ProxyWebDriverFactory(proxy).setupWebDriver());
            } else {
                DRIVER.set(new LocalWebDriverFactory().setupWebDriver());
            }
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
