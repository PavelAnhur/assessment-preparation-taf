package com.epam.core.webdriver.factory;

import com.epam.core.enums.Browser;
import com.epam.core.exceptions.ProxyWebDriverException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;

public class ProxyWebDriverFactory implements IWebDriver {
    private final Proxy proxy;

    public ProxyWebDriverFactory(final Proxy pr) {
        this.proxy = pr;
    }

    @Override
    public WebDriver setupWebDriver() throws ProxyWebDriverException {
        String browserName = System.getProperty("browser");
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        WebDriver driver;
        switch (browser) {
            case FIREFOX:
                driver = getFirefoxProxyDriver();
                break;
            case OPERA:
                driver = getOperaProxyDriver();
                break;
            case CHROME:
                driver = getChromeProxyDriver();
                break;
            default:
                throw new ProxyWebDriverException("Can't create web driver");
        }
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver getChromeProxyDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        setOptions(options);
        return new ChromeDriver(options);
    }

    private WebDriver getFirefoxProxyDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-web-security");
        setOptions(options);
        return new FirefoxDriver(options);
    }

    private WebDriver getOperaProxyDriver() {
        WebDriverManager.operadriver().setup();
        OperaOptions options = new OperaOptions();
        options.addArguments("--disable-web-security");
        setOptions(options);
        return new OperaDriver(options);
    }

    private void setOptions(final MutableCapabilities options) {
        options.setCapability(CapabilityType.PROXY, proxy);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    }
}
