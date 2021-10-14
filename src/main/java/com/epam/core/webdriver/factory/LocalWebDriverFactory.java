package com.epam.core.webdriver.factory;

import com.epam.core.enums.Browser;
import com.epam.core.exceptions.LocalWebDriverException;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;

@Slf4j
@AllArgsConstructor
public class LocalWebDriverFactory implements IWebDriver {

    @Override
    public WebDriver setupWebDriver() throws LocalWebDriverException {
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
            case CHROME:
                driver = getChromeDriver();
                break;
            default:
                throw new LocalWebDriverException("Can't create web driver");
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
}
