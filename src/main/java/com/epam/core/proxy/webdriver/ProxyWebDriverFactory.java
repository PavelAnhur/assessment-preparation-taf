package com.epam.core.proxy.webdriver;

import com.epam.core.exceptions.CustomProjectException;
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

public class ProxyWebDriverFactory {
    private final Proxy proxy;

    public ProxyWebDriverFactory(Proxy proxy) {
        this.proxy = proxy;
    }

    public WebDriver getProxyWebDriver() {
        String browserName = System.getProperty("browser");
        WebDriver driver;
        if (null == browserName) {
            throw new CustomProjectException("browser name can't be null");
        } else {
            switch (browserName) {
                case "firefox":
                    driver = getFirefoxProxyDriver();
                    break;
                case "opera":
                    driver = getOperaProxyDriver();
                    break;
                case "chrome":
                default:
                    driver = getChromeProxyDriver();
            }
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

    private void setOptions(MutableCapabilities options) {
        options.setCapability(CapabilityType.PROXY, proxy);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    }
}
