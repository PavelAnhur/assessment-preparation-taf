package com.epam.tests.kinopoisk;

import com.browserup.bup.BrowserUpProxyServer;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.annotation.Proxy;
import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.configuration.proxy.BrowserUpProxyServerSingleton;
import com.epam.core.webdriver.WebDriverSingleton;
import com.epam.steps.BrowserUpMobProxySteps;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
public class BrowserUpProxyTest {
    private static final String INITIAL_PAGE_REF = ConfigurationManager.configuration().initialPageRef();
    private static final String HAR_FILE_NAME = ConfigurationManager.configuration().harFileName();
    private static final String HAR_PATHNAME = "src/test/resources/har/" + HAR_FILE_NAME;
    private static final int NUMBER_PNG_FILES = 5;
    private final BrowserUpProxyServer browserUpProxyServer = BrowserUpProxyServerSingleton.getInstance();
    private BrowserUpMobProxySteps testSteps;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        WebDriverRunner.setWebDriver(WebDriverSingleton.getDriver());
        testSteps = new BrowserUpMobProxySteps(browserUpProxyServer);
    }

    @Proxy
    @Test()
    public void browserMobProxyTest() {
        browserUpProxyServer.newHar(INITIAL_PAGE_REF);
        testSteps.openHomePage()
                .createHarFile(HAR_PATHNAME);

        Assertions.assertThat(
                testSteps.getPngFilesCountFromHarFile(HAR_PATHNAME)
        ).isGreaterThan(
                NUMBER_PNG_FILES);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        BrowserUpProxyServerSingleton.stopProxyServer();
        WebDriverSingleton.closeDriver();
    }
}
