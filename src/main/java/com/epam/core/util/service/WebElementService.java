package com.epam.core.util.service;

import com.epam.core.util.WaiterUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.epam.core.constant.KinopoiskConstants.TOP;

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

    public static Map<String, String> getMapOfElementText(final String key, final String value) {
        Map<String, String> ratingsForManufacturedYearMap = new HashMap<>();
        final String xpathLocatorPattern = "//*[@class='film-item-rating-position__position' and text()='%d']%s";
        for (int i = 1; i <= TOP; i++) {
            String keyXpathLocator = String.format(xpathLocatorPattern, i, "/../../..//*[contains(@class,'" + key + "')]");
            String valueXpathLocator = String.format(xpathLocatorPattern, i, "/../../..//*[starts-with(@class,'" + value + "')]");
            String entryKey = $(By.xpath(keyXpathLocator)).getText();
            ratingsForManufacturedYearMap.put(entryKey, $(By.xpath(valueXpathLocator)).getText());
            if ((i % 50 == 0) && i < TOP) {
                $(By.xpath("//*[text()='Вперёд']")).click();
            }
        }

        ratingsForManufacturedYearMap
                .forEach((entryKey, entryValue) -> log.info("Key = '{}' Value = '{}'", entryKey, entryValue));

        return ratingsForManufacturedYearMap;
    }
}
