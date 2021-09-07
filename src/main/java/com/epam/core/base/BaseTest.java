package com.epam.core.base;

import com.epam.core.webdriver.WebDriverSingleton;
import org.testng.annotations.AfterClass;

public abstract class BaseTest {

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}
