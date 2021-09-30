package com.epam.steps;

import com.codeborne.selenide.Selenide;
import com.epam.base.BaseStep;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class AdvanceSearchSteps extends BaseStep {

    public AdvanceSearchSteps clickAdvancedSearchButton() {
        log.info("click advance search button");
        $(By.xpath("//a[@aria-label='advanced-search']")).click();
        return this;
    }

    public AdvanceSearchSteps selectCountry(final String country) {
        log.info("select country from dropdown");
        $("#country").click();
        $(By.xpath(String.format("//option[text()='%s']", country))).click();
        return this;
    }

    public AdvanceSearchSteps selectGenre(List<String> genreList) {
        WebElement genreSelectBox = $(By.id("m_act[genre]"));
        Selenide.actions().moveToElement(genreSelectBox).build().perform();

        log.info("select {} genres", genreList);
        Select select = new Select(genreSelectBox);
        genreList.forEach(select::selectByVisibleText);
        return this;
    }

    public AdvanceSearchSteps confirmGenre() {
        log.info("select genre's checkbox");
        $(By.xpath("//input[@id='m_act[genre_and]']")).setSelected(true);
        return this;
    }

    public void clickSearchButton() {
        log.info("click search button");
        $("input.el_18.submit.nice_button").click();
    }

    public String getActualSearchResult() {
        String actualSearchResult = $(By.xpath("//span[@class='num' and text()='1']/..//p[@class='name']/a")).getText();
        log.info("search result: '{}'", actualSearchResult);
        return actualSearchResult;
    }
}
