package com.epam.tests.kinopoisk;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.annotation.Proxy;
import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.configuration.proxy.BrowserMobProxyServerSingleton;
import com.epam.core.webdriver.WebDriverSingleton;
import com.epam.steps.BrowserMobProxySteps;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
public class BrowserMobProxyTest {
    private static final String INITIAL_PAGE_REF = ConfigurationManager.configuration().initialPageRef();
    private static final String HAR_FILE_NAME = ConfigurationManager.configuration().harFileName();
    private static final String HAR_PATHNAME = "src/test/resources/har/" + HAR_FILE_NAME;
    private static final int NUMBER_PNG_FILES = 5;
    private final BrowserMobProxyServer browserMobProxyServer = BrowserMobProxyServerSingleton.getInstance();
    private BrowserMobProxySteps testSteps;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        WebDriverRunner.setWebDriver(WebDriverSingleton.getDriver());
        testSteps = new BrowserMobProxySteps(browserMobProxyServer);
    }

    @Proxy
    @Test()
    public void browserMobProxyTest() {
        browserMobProxyServer.newHar(INITIAL_PAGE_REF);
        testSteps.openHomePage()
                .createHarFile(HAR_PATHNAME);

        Assertions.assertThat(
                testSteps.getPngFilesCountFromHarFile(HAR_PATHNAME)
        ).isGreaterThan(
                NUMBER_PNG_FILES);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        BrowserMobProxyServerSingleton.stopProxyServer();
        WebDriverSingleton.closeDriver();
    }
}
