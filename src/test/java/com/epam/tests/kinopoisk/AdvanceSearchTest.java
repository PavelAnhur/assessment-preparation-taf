package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.dataprovider.KinopoiskTestsDataProvider;
import com.epam.steps.AdvanceSearchSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.core.util.reflection.StepsManager.getSteps;

public class AdvanceSearchTest extends BaseTest {
    private final Class<AdvanceSearchSteps> advanceSearchStepsClass = AdvanceSearchSteps.class;

    @Test(dataProvider = "advancedSearchTestDataProvider", dataProviderClass = KinopoiskTestsDataProvider.class)
    public void kinopoiskAdvanceSearchTest(String country, List<String> genreList, String expectedSearchResult) {

        getSteps(advanceSearchStepsClass)
                .openHomePage();
        getSteps(advanceSearchStepsClass)
                .clickAdvancedSearchButton()
                .selectCountry(country)
                .selectGenre(genreList)
                .confirmGenre()
                .clickSearchButton();

        Assert.assertEquals(getSteps(advanceSearchStepsClass).getActualSearchResult(), expectedSearchResult,
                String.format("Search result doesn't contain '%s'", expectedSearchResult));
    }
}
