package com.epam.pages.kinopoisk;

import com.codeborne.selenide.SelenideElement;
import com.epam.core.config.PropertyDataReader;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private final String homePageUrl = PropertyDataReader.getPropertyValue("homePage");
    private final SelenideElement advanceSearchButton = $(By.xpath("//a[@aria-label='advanced-search']"));
    private final SelenideElement top250Link = $(By.xpath("//*[contains(@class,'middle')]//*[@href='/lists/top250/']"));

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void clickAdvancedSearchButton() {
        advanceSearchButton.click();
    }

    public void clickTop250Link() {
        top250Link.click();
    }
}
