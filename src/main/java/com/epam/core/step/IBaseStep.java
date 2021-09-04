package com.epam.core.step;

import org.openqa.selenium.WebDriver;

public interface IBaseStep {

    WebDriver getDriver();

    void close();
}
