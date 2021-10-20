package com.epam.steps;

import com.epam.core.configuration.property.ConfigurationManager;
import com.epam.core.utility.service.WebElementService;
import com.epam.pages.kinopoisk.HomePage;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class AmountOfViewsSteps {
    private final HomePage homePage;

    public AmountOfViewsSteps() {
        homePage = new HomePage();
    }

    public AmountOfViewsSteps openHomePage() {
        open(homePage.getHomePageUrl());
        log.info("open home page '{}'", homePage.getHomePageUrl());
        return this;
    }

    public void clickTop250link() {
        log.info("click on top{} link", ConfigurationManager.configuration().topFilms());
        homePage.clickFilmsLink();
        homePage.getTop250Link().click();
    }

    public long getFilmsNumberWithViewsLessThanCertain(final int viewsForComparison) {
        long amount = WebElementService.getMapOfElementText("original-name", "rating__count")
                .values()
                .stream()
                .map(votes -> votes.replace(" ", ""))
                .collect(Collectors.toList())
                .stream()
                .filter(votes -> Integer.parseInt(votes) < viewsForComparison)
                .peek(log::info)
                .count();

        log.info("amount of films with views less than {} -> '{}'", viewsForComparison, amount);
        return amount;
    }
}
