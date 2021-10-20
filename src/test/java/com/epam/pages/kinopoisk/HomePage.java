package com.epam.pages.kinopoisk;

import com.codeborne.selenide.SelenideElement;
import com.epam.core.configuration.property.ConfigurationManager;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private final String homePageUrl = ConfigurationManager.configuration().homePage();
    private final SelenideElement advanceSearchButton = $(By.xpath("//a[@aria-label='advanced-search']"));
    private final SelenideElement top250Link = $(By.xpath("//*[@href='/lists/top250/']"));
    private final SelenideElement filmsLink = $(By.xpath("//*[contains(@class,'middle')]//*[@href='/lists/films/1/']"));

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void clickAdvancedSearchButton() {
        advanceSearchButton.click();
    }

    public SelenideElement getTop250Link() {
        return top250Link;
    }

    public void clickFilmsLink() {
        filmsLink.click();
    }
}
