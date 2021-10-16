package com.epam.core.utility.service;

import com.epam.core.configuration.property.ConfigurationManager;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
@UtilityClass
public final class WebElementService {
    private static final int RESULTS_AMOUNT_ON_PAGE = 50;
    private static final String FORWARD_BUTTON_LOCATOR = "//*[text()='Вперёд']";
    private final int top = ConfigurationManager.configuration().topFilms();
    private final String filmInfoXpathLocatorPattern =
            "//*[@class='film-item-rating-position__position' and text()='%d']/ancestor::div[contains(@class, 'film-item')]%s";

    public Map<String, String> getMapOfElementText(final String key, final String value) {
        Map<String, String> ratingsForManufacturedYearMap = new HashMap<>();

        IntStream.rangeClosed(1, top).forEach(i -> {
            String keyXpathLocator =
                    String.format(filmInfoXpathLocatorPattern, i, "//*[contains(@class,'" + key + "')]");
            String valueXpathLocator =
                    String.format(filmInfoXpathLocatorPattern, i, "//*[starts-with(@class,'" + value + "')]");
            String entryKey = $(By.xpath(keyXpathLocator)).getText();
            ratingsForManufacturedYearMap.put(entryKey, $(By.xpath(valueXpathLocator)).getText());
            if ((i % RESULTS_AMOUNT_ON_PAGE == 0) && i < top) {
                $(By.xpath(FORWARD_BUTTON_LOCATOR)).click();
            }
        });

        ratingsForManufacturedYearMap
                .forEach((entryKey, entryValue) -> log.info("Key = '{}' Value = '{}'", entryKey, entryValue));

        return ratingsForManufacturedYearMap;
    }
}
