package com.epam.core.webdriver.factory;

import com.epam.core.reflection.ClassScanner;
import org.openqa.selenium.Proxy;

public class WebDriverFactory implements AbstractDriverFactory<IWebDriver> {
    private final Proxy proxy;

    public WebDriverFactory(final Proxy pr) {
        this.proxy = pr;
    }

    @Override
    public IWebDriver create() {
        return ClassScanner.isAnnotatedWithProxy() ? new ProxyWebDriverFactory(proxy) : new LocalWebDriverFactory();
    }
}
