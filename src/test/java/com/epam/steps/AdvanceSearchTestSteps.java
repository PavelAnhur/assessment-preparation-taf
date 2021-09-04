package com.epam.steps;

import com.epam.core.step.BaseStep;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class AdvanceSearchTestSteps extends BaseStep {
    private static final String HOME_PAGE_URL = "https://www.kinopoisk.ru/";

    public AdvanceSearchTestSteps openHomePage() {
        open(HOME_PAGE_URL);
        log.info("open home page '{}'", HOME_PAGE_URL);
        return this;
    }

    public AdvanceSearchTestSteps clickAdvancedSearchButton() {
        log.info("click advance search button");
        $(By.xpath("//a[@aria-label='advanced-search']")).click();
        return this;
    }

    public AdvanceSearchTestSteps selectCountry(final String country) {
        log.info("select country from dropdown");
        $("#country").click();
        $(By.xpath(String.format("//option[text()='%s']", country))).click();
        return this;
    }

    public AdvanceSearchTestSteps selectGenre(List<String> genreList) {
        int yPoint = getDriver().manage().window().getSize().getHeight() / 2;
        log.info("get in the middle of the screen '{}'", yPoint);
        executeJavaScript(String.format("window.scrollBy(0, %s);", yPoint));

        log.info("select {} genres", genreList);
        Select select = new Select(getDriver().findElement(By.id("m_act[genre]")));
        genreList.forEach(select::selectByVisibleText);
        return this;
    }

    public AdvanceSearchTestSteps confirmGenre() {
        log.info("select genre's checkbox");
        $(By.xpath("//input[@id='m_act[genre_and]']")).setSelected(true);
        return this;
    }

    public AdvanceSearchTestSteps clickSearchButton() {
        log.info("click search button");
        $("input.el_18.submit.nice_button").click();
        return this;
    }

    public void validateResult(final String expectedSearchResult) {
        log.info("validating result..");
        Assert.assertEquals(
                $(By.xpath("//span[@class='num' and text()='1']/..//p[@class='name']/a")).getText(),
                expectedSearchResult,
                String.format("Search result doesn't contain '%s'", expectedSearchResult));
    }
}