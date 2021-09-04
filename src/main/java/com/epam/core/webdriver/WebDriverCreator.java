package com.epam.core.webdriver;

import com.epam.core.exceptions.CustomProjectException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class WebDriverCreator {
    private final WebDriverFactory webDriverFactory;

    public WebDriverCreator() {
        this.webDriverFactory = new WebDriverFactory();
    }

    public WebDriver setupWebDriver() {
        String browserName = System.getProperty("browser");
        WebDriver driver;
        if (null == browserName) {
            throw new CustomProjectException("browser name can't be null");
        } else {
            switch (browserName) {
                case "firefox":
                    driver = webDriverFactory.getFirefoxDriver();
                    break;
                case "opera":
                    driver = webDriverFactory.getOperaDriver();
                    break;
                case "remoteChrome":
                    driver = webDriverFactory.getRemoteChromeDriver();
                    break;
                case "remoteFirefox":
                    driver = webDriverFactory.getRemoteFirefoxDriver();
                    break;
                case "chrome":
                default:
                    driver = webDriverFactory.getChromeDriver();
            }
        }
        driver.manage().window().maximize();
        return driver;
    }
}
