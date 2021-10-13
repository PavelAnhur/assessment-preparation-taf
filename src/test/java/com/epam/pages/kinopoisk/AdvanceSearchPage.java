package com.epam.pages.kinopoisk;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AdvanceSearchPage {
    private static final String COUNTRY_FIELD_LOCATOR = "//option[text()='%s']";
    private final SelenideElement countryCheckbox = $("#country");
    private final SelenideElement genreSelectBox = $(By.id("m_act[genre]"));
    private final SelenideElement genreCheckbox = $(By.xpath("//input[@id='m_act[genre_and]']"));
    private final SelenideElement searchButton = $("input.el_18.submit.nice_button");

    public void clickOnCountryDropdown() {
        countryCheckbox.click();
    }

    public void selectCountry(final String country) {
        $(By.xpath(String.format(COUNTRY_FIELD_LOCATOR, country))).click();
    }

    public SelenideElement getGenreSelectBox() {
        return genreSelectBox;
    }

    public void selectGenreCheckbox(boolean flag) {
        genreCheckbox.setSelected(flag);
    }

    public void clickSearchButton() {
        searchButton.click();
    }
}
