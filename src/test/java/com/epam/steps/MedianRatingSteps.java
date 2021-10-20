package com.epam.steps;

import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.exceptions.KinopoiskProjectException;
import com.epam.core.utility.service.WebElementService;
import com.epam.pages.kinopoisk.HomePage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class MedianRatingSteps {
    private final HomePage homePage;

    public MedianRatingSteps() {
        this.homePage = new HomePage();
    }

    public MedianRatingSteps openHomePage() {
        open(homePage.getHomePageUrl());
        log.info("open home page '{}'", homePage.getHomePageUrl());
        return this;
    }

    public void clickTop250link() {
        log.info("click on top{} link", ConfigurationManager.configuration().topFilms());
        homePage.clickFilmsLink();
        homePage.getTop250Link().click();
    }

    public Map<String, String> getTop250Map() {
        return WebElementService.getMapOfElementText("original-name", "rating__value");
    }

    public double getAverageRatingForManufacturedYear(final Map<String, String> top250Map, final int year) {
        List<String> ratingList = new ArrayList<>();

        top250Map.entrySet()
                .stream()
                .filter(entryKey -> entryKey.getKey().endsWith(String.valueOf(year)))
                .forEach(entry -> {
                    ratingList.add(entry.getValue());
                    log.info("Name = '{}', Rating = '{}'", entry.getKey(), entry.getValue());
                });

        double averageRating = ratingList.stream()
                .mapToDouble(Double::parseDouble)
                .average()
                .stream()
                .findFirst()
                .orElseThrow(() -> new KinopoiskProjectException("average rating calculation failed"));

        log.info("average rating for '{}' manufactured year: '{}'", year, averageRating);

        return averageRating;
    }
}
