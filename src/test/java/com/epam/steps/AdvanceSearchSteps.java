package com.epam.steps;

import com.codeborne.selenide.Selenide;
import com.epam.pages.kinopoisk.AdvanceSearchPage;
import com.epam.pages.kinopoisk.HomePage;
import com.epam.pages.kinopoisk.SearchResultPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class AdvanceSearchSteps {
    private final HomePage homePage;
    private final AdvanceSearchPage advanceSearchPage;
    private final SearchResultPage searchResultPage;

    public AdvanceSearchSteps() {
        this.homePage = new HomePage();
        this.advanceSearchPage = new AdvanceSearchPage();
        this.searchResultPage = new SearchResultPage();
    }

    public AdvanceSearchSteps openHomePage() {
        open(homePage.getHomePageUrl());
        log.info("open home page '{}'", homePage.getHomePageUrl());
        return this;
    }

    public AdvanceSearchSteps clickAdvanceSearchButton() {
        log.info("click advance search button");
        homePage.clickAdvancedSearchButton();
        return this;
    }

    public void searchForParticularFilm(final String country, final List<String> genreList) {
        log.info("select country from dropdown");
        advanceSearchPage.clickOnCountryDropdown();
        advanceSearchPage.selectCountry(country);

        Selenide.actions().moveToElement(advanceSearchPage.getGenreSelectBox()).build().perform();
        log.info("select {} genres", genreList);
        Select select = new Select(advanceSearchPage.getGenreSelectBox());
        genreList.forEach(select::selectByVisibleText);

        log.info("select genre's checkbox");
        advanceSearchPage.selectGenreCheckbox();

        log.info("click search button");
        advanceSearchPage.clickSearchButton();
    }

    public String getActualSearchResult() {
        String actualSearchResult = searchResultPage.getTextFromSearchResultFilmElement();
        log.info("search result: '{}'", actualSearchResult);
        return actualSearchResult;
    }
}
