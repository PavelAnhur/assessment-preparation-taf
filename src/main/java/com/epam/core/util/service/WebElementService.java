package com.epam.core.util.service;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.epam.core.constant.KinopoiskConstants.TOP;

@Slf4j
public final class WebElementService {

    private WebElementService() {
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
