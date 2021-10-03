package com.epam.core.webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.config.PropertyDataReader;
import com.epam.core.enums.Browser;
import com.epam.core.exceptions.CustomProjectException;
import com.epam.core.exceptions.RemoteWebDriverException;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.openqa.selenium.remote.CapabilityType.BROWSER_NAME;

@Slf4j
@AllArgsConstructor
public class WebDriverFactory implements IWebDriverFactory {
    private static final String VIRTUAL_URL = PropertyDataReader.getPropertyValue("virtualUrl");

    @Override
    public WebDriver setupWebDriver() throws CustomProjectException {
        String browserName = System.getProperty("browser");
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        WebDriver driver;
        switch (browser) {
            case FIREFOX:
                driver = getFirefoxDriver();
                break;
            case OPERA:
                driver = getOperaDriver();
                break;
            case REMOTECHROME:
                driver = getRemoteChromeDriver();
                break;
            case REMOTEFIREFOX:
                driver = getRemoteFirefoxDriver();
                break;
            case CHROME:
                driver = getChromeDriver();
                break;
            default:
                throw new CustomProjectException("Can't create web driver");
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
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setCapability(BROWSER_NAME, BrowserType.CHROME);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        cap.setPlatform(Platform.ANY);
        cap.setCapability("chrome.switches", List.of("--disable-notifications"));

        Configuration.browserCapabilities = cap;
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;

        Configuration.remote = VIRTUAL_URL;
        WebDriverRunner.setWebDriver(getRemoteWebDriver(cap));

        return WebDriverRunner.getWebDriver();
    }

    private WebDriver getRemoteFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(BROWSER_NAME, BrowserType.FIREFOX);
        return getRemoteWebDriver(firefoxOptions);
    }

    private RemoteWebDriver getRemoteWebDriver(final Capabilities options) throws RemoteWebDriverException {
        try {
            return new RemoteWebDriver(new URL(VIRTUAL_URL), options);
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
        throw new RemoteWebDriverException("Failed to create remote web driver!");
    }
}
