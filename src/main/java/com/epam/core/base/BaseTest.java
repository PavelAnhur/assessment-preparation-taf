package com.epam.core.base;

import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterTest;

public abstract class BaseTest {

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        WebDriverRunner.closeWebDriver();
    }
}
