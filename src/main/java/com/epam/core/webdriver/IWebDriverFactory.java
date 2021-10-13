package com.epam.core.webdriver;

import com.epam.core.exceptions.LocalWebDriverException;
import com.epam.core.exceptions.ProxyWebDriverException;
import org.openqa.selenium.WebDriver;

public interface IWebDriverFactory {

    WebDriver setupWebDriver() throws LocalWebDriverException, ProxyWebDriverException;
}
