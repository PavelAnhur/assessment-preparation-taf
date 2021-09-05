package com.epam.core.util.service;

import com.epam.core.util.WaiterUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.executeJavaScript;

@Slf4j
public final class WebElementService {
    private static final int NUMBER_OF_ATTEMPTS = 20;

    private WebElementService() {
    }

    public static void scrollToElement(final WebDriver driver, final WebElement element) {
        log.info("scroll to element '{}'", element.getTagName());
        int yPoint = driver.manage().window().getSize().getHeight();

        Supplier<Boolean> isElementPositionCorrect = () -> {
            Point elementPoint = element.getLocation();
            log.info("coordinates of the element are: x '{}', y '{}'", elementPoint.getX(), elementPoint.getY());
            if (elementPoint.getY() > yPoint * 0.8) {
                log.info("element still at the bottom of the screen");
                executeJavaScript(String.format("window.scrollBy(0, %s);", yPoint * 0.1));
            } else {
                log.info("element near the middle of the screen");
                return true;
            }
            return false;
        };

        WaiterUtil.waitForTrue(isElementPositionCorrect, NUMBER_OF_ATTEMPTS, "Element doesn't exist");
    }
}
