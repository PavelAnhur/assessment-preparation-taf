package com.epam.tests.kinopoisk;

import com.epam.core.annotation.Proxy;
import com.epam.core.config.PropertyDataReader;
import com.epam.core.proxy.SeleniumProxyConfigurator;
import com.epam.core.webdriver.WebDriverSingleton;
import com.epam.steps.BrowserMobProxyTestSteps;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrowserMobProxyTest {
    private static final String INITIAL_PAGE_REF = PropertyDataReader.getPropertyValue("initialPageRef");
    private static final String HAR_FILE_NAME = PropertyDataReader.getPropertyValue("harFileName");
    private static final String HAR_PATHNAME = "src/test/resources/har/" + HAR_FILE_NAME;
    private static final int NUMBER_PNG_FILES = 5;
    private static final int PORT = 8080;
    private BrowserMobProxyServer proxy;
    private WebDriver driver;
    private BrowserMobProxyTestSteps testSteps;

    @BeforeClass
    @Proxy
    public void setup() {
        proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start(PORT);
        driver = new WebDriverSingleton(SeleniumProxyConfigurator.configureProxy(proxy)).getDriver();
        testSteps = new BrowserMobProxyTestSteps(driver, proxy);
    }

    @Test
    public void browserMobProxyTest() {
        proxy.newHar(INITIAL_PAGE_REF);
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
