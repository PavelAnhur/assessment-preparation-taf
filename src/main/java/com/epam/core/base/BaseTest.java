package com.epam.core.base;

import com.codeborne.selenide.WebDriverRunner;
import com.epam.core.webdriver.WebDriverSingleton;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {
        WebDriverRunner.setWebDriver(new WebDriverSingleton().getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}
