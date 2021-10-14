package com.epam.core.webdriver;

import com.epam.core.util.reflection.ClassScanner;
import org.openqa.selenium.Proxy;

public class WebDriverFactory implements AbstractDriverFactory<IWebDriver> {
    private final Proxy proxy;

    public WebDriverFactory(final Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public IWebDriver create() {
        return ClassScanner.isAnnotatedWithProxy() ? new ProxyWebDriverFactory(proxy) : new LocalWebDriverFactory();
    }
}
