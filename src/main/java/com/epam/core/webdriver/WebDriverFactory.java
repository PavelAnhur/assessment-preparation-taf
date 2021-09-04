package com.epam.core.webdriver;

import com.epam.core.exceptions.CustomProjectException;
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
    private static final String VIRTUAL_URL = "http://localhost:4444/wd/hub";

    public WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en");
        FirefoxOptions firefoxOptionsLocal = new FirefoxOptions();
        firefoxOptionsLocal.setProfile(profile);
        return new FirefoxDriver(firefoxOptionsLocal);
    }

    public WebDriver getOperaDriver() {
        WebDriverManager.operadriver().setup();
        return new OperaDriver();
    }

    public WebDriver getRemoteChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(BROWSER_NAME, BrowserType.CHROME);
        return getRemoteWebDriver(chromeOptions);
    }

    public WebDriver getRemoteFirefoxDriver() {
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
        throw new CustomProjectException("Failed to create web driver!");
    }
}
