package com.epam.core.configuration.webdriver;

import com.codeborne.selenide.Configuration;
import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.enums.Browser;
import com.epam.core.exceptions.RemoteWebDriverException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.List;

import static com.epam.core.enums.Browser.CHROME;
import static com.epam.core.enums.Browser.EDGE;
import static com.epam.core.enums.Browser.FIREFOX;

@Slf4j
public class RemoteWebDriverTuner {
    private static final int TIMEOUT = 10_000;

    public void configRemoteWebDriver() {
        String browserName = ConfigurationManager.configuration().browser();
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        switch (browser) {
            case FIREFOX:
                configRemoteFirefoxWebDriver();
                break;
            case CHROME:
                configRemoteChromeWebDriver();
                break;
            case EDGE:
                configRemoteEdgeWebDriver();
                break;
            default:
                throw new RemoteWebDriverException("Can't set remote web driver up");
        }

        Configuration.timeout = TIMEOUT;
        Configuration.startMaximized = true;
    }

    private void configRemoteFirefoxWebDriver() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(FIREFOX.getValue());
        cap.setPlatform(Platform.ANY);
        cap.setCapability("firefox.switches", List.of("--disable-notifications"));

        Configuration.browserCapabilities = cap;
        Configuration.browser = FIREFOX.getValue();
    }

    private void configRemoteEdgeWebDriver() {
        EdgeOptions options = new EdgeOptions();
        HashMap<String, Object> edgePrefs = new HashMap<>();
        edgePrefs.put("profile.default_content_settings.popups", 0);
        options.setCapability("prefs", edgePrefs);
        options.setCapability("useAutomationExtension", false);

        Configuration.browserCapabilities = options;
        Configuration.browser = EDGE.getValue();
    }

    private void configRemoteChromeWebDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--no-sandbox");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        cap.setBrowserName(CHROME.getValue());
        cap.setPlatform(Platform.ANY);
        cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        Configuration.browserCapabilities = cap;
        Configuration.browser = CHROME.getValue();
    }
}
