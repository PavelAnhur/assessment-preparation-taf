package com.epam.core.webdriver.factory;

import com.epam.core.exceptions.LocalWebDriverException;
import com.epam.core.exceptions.ProxyWebDriverException;
import org.openqa.selenium.WebDriver;

public interface IWebDriver {

    WebDriver setupWebDriver() throws LocalWebDriverException, ProxyWebDriverException;
}
