package com.epam.tests.kinopoisk;

import com.epam.core.proxy.webdriver.ProxyWebDriverFactory;
import com.epam.steps.BrowserMobProxyTestSteps;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epam.core.proxy.SeleniumProxyConfigurator.configureProxy;

public class BrowserMobProxyTest {
    private static final String HAR_PATHNAME = "src/test/resources/har/kinopoisk.har";
    private static final int NUMBER_PNG_FILES = 5;
    private BrowserMobProxyServer proxy;
    private WebDriver driver;
    private BrowserMobProxyTestSteps testSteps;

    @BeforeClass
    public void setup() {
        proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start(8080);
        driver = new ProxyWebDriverFactory(configureProxy(proxy)).getProxyWebDriver();
        testSteps = new BrowserMobProxyTestSteps(driver, proxy);
    }

    @Test
    public void browserMobProxyTest() {
        proxy.newHar("kinopoisk.ru");
        testSteps.openHomePage()
                .createHarFile(HAR_PATHNAME);
        Assertions.assertThat(testSteps.getPngFilesCountFromHarFile(HAR_PATHNAME)).isGreaterThan(NUMBER_PNG_FILES);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        proxy.stop();
        driver.quit();
    }
}
