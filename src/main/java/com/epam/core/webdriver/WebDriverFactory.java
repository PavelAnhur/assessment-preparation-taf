package com.epam.core.webdriver;

import com.epam.core.config.PropertyDataReader;
import com.epam.core.exceptions.CustomProjectException;
import com.epam.core.exceptions.RemoteWebDriverException;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;

@Slf4j
public class WebDriverFactory {
    private static final String VIRTUAL_URL = PropertyDataReader.getPropertyValue("virtualUrl");

    public WebDriver setupWebDriver() {
        String browserName = System.getProperty("browser");
        WebDriver driver;
        if (null == browserName) {
            throw new CustomProjectException("browser name can't be null");
        } else {
            switch (browserName) {
                case "firefox":
                    driver = getFirefoxDriver();
                    break;
                case "opera":
                    driver = getOperaDriver();
                    break;
                case "remoteChrome":
                    driver = getRemoteChromeDriver();
                    break;
                case "remoteFirefox":
                    driver = getRemoteFirefoxDriver();
                    break;
                case "chrome":
                default:
                    driver = getChromeDriver();
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en");
        FirefoxOptions firefoxOptionsLocal = new FirefoxOptions();
        firefoxOptionsLocal.setProfile(profile);
        return new FirefoxDriver(firefoxOptionsLocal);
    }

    private WebDriver getOperaDriver() {
        WebDriverManager.operadriver().setup();
        return new OperaDriver();
    }

    private WebDriver getRemoteChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(BROWSER_NAME, BrowserType.CHROME);
        return getRemoteWebDriver(chromeOptions);
    }

    private WebDriver getRemoteFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(BROWSER_NAME, BrowserType.FIREFOX);
        return getRemoteWebDriver(firefoxOptions);
    }

    private RemoteWebDriver getRemoteWebDriver(Capabilities options) {
        try {
            return new RemoteWebDriver(new URL(VIRTUAL_URL), options);
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
        throw new RemoteWebDriverException("Failed to create remote web driver!");
    }
}
