package com.epam.core.webdriver.factory;

import com.epam.core.reflection.ClassScanner;

public class WebDriverFactory implements AbstractDriverFactory<IWebDriver> {

    public WebDriverFactory() {
    }

    @Override
    public IWebDriver create() {
//        return ClassScanner.isAnnotatedWithProxy() ? new ProxyWebDriverFactory() : new LocalWebDriverFactory();
        ClassScanner.isAnnotatedWithProxy();
        return new LocalWebDriverFactory();
    }
}
