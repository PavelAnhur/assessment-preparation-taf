package com.epam.core.base;

import com.epam.core.webdriver.WebDriverSingleton;
import org.testng.annotations.AfterTest;

public abstract class BaseTest {

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}
