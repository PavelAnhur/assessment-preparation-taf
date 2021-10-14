package com.epam.tests.kinopoisk;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.annotation.Proxy;
import com.epam.core.config.PropertyDataReader;
import com.epam.core.proxy.SeleniumProxyConfigurator;
import com.epam.core.webdriver.WebDriverSingleton;
import com.epam.steps.BrowserMobProxySteps;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrowserMobProxyTest {
    private static final String INITIAL_PAGE_REF = PropertyDataReader.getPropertyValue("initialPageRef");
    private static final String HAR_FILE_NAME = PropertyDataReader.getPropertyValue("harFileName");
    private static final String HAR_PATHNAME = "src/test/resources/har/" + HAR_FILE_NAME;
    private static final int NUMBER_PNG_FILES = 5;
    private static final int PORT = 8080;
    private BrowserMobProxyServer mobProxyServer;
    private BrowserMobProxySteps testSteps;

    @Proxy
    @BeforeClass(alwaysRun = true)
    public void setup() {
        mobProxyServer = new BrowserMobProxyServer();
        mobProxyServer.setTrustAllServers(true);
        mobProxyServer.start(PORT);
        WebDriverRunner.setWebDriver(
                WebDriverSingleton.getDriver(SeleniumProxyConfigurator.configureProxy(mobProxyServer))
        );
        testSteps = new BrowserMobProxySteps(mobProxyServer);
    }

    @Test(singleThreaded = true)
    public void browserMobProxyTest() {
        mobProxyServer.newHar(INITIAL_PAGE_REF);
        testSteps.openHomePage()
                .createHarFile(HAR_PATHNAME);
        Assertions.assertThat(
                        testSteps.getPngFilesCountFromHarFile(HAR_PATHNAME))
                .isGreaterThan(
                        NUMBER_PNG_FILES);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        mobProxyServer.stop();
        WebDriverSingleton.closeDriver();
    }
}
