package com.epam.core.utility.service;

import com.epam.core.configuration.property.PropertyDataReader;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
@UtilityClass
public final class WebElementService {
    private static final int RESULTS_AMOUNT_ON_PAGE = 50;
    private final int top = Integer.parseInt(PropertyDataReader.getPropertyValue("topFilms"));

    public Map<String, String> getMapOfElementText(final String key, final String value) {
        Map<String, String> ratingsForManufacturedYearMap = new HashMap<>();
        final String xpathLocatorPattern =
                "//*[@class='film-item-rating-position__position' and text()='%d']/ancestor::div[contains(@class, 'film-item')]%s";
        for (int i = 1; i <= top; i++) {
            String keyXpathLocator = String.format(xpathLocatorPattern, i, "//*[contains(@class,'" + key + "')]");
            String valueXpathLocator =
                    String.format(xpathLocatorPattern, i, "//*[starts-with(@class,'" + value + "')]");
            String entryKey = $(By.xpath(keyXpathLocator)).getText();
            ratingsForManufacturedYearMap.put(entryKey, $(By.xpath(valueXpathLocator)).getText());
            if ((i % RESULTS_AMOUNT_ON_PAGE == 0) && i < top) {
                $(By.xpath("//*[text()='Вперёд']")).click();
            }
        }

        ratingsForManufacturedYearMap
                .forEach((entryKey, entryValue) -> log.info("Key = '{}' Value = '{}'", entryKey, entryValue));

        return ratingsForManufacturedYearMap;
    }
}