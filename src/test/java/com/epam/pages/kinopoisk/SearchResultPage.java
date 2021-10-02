package com.epam.pages.kinopoisk;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage {
    private final SelenideElement searchResultFilm =
            $(By.xpath("//span[@class='num' and text()='1']/..//p[@class='name']/a"));

    public String getTextFromSearchResultFilm() {
        return searchResultFilm.getText();
    }
}
