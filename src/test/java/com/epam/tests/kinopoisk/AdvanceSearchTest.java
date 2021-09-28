package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.dataprovider.KinopoiskTestsDataProvider;
import com.epam.steps.AdvanceSearchTestSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.core.util.reflection.StepsManager.getSteps;

public class AdvanceSearchTest extends BaseTest {
    private final Class<AdvanceSearchTestSteps> advanceSearchTestStepsClass = AdvanceSearchTestSteps.class;

    @Test(dataProvider = "advancedSearchTestDataProvider", dataProviderClass = KinopoiskTestsDataProvider.class)
    public void kinopoiskAdvanceSearchTest(final String country, final List<String> genreList,
                                           final String expectedSearchResult) {

        getSteps(advanceSearchTestStepsClass)
                .openHomePage();
        getSteps(advanceSearchTestStepsClass)
                .clickAdvancedSearchButton()
                .selectCountry(country)
                .selectGenre(genreList)
                .confirmGenre()
                .clickSearchButton();

        Assert.assertEquals(getSteps(advanceSearchTestStepsClass).getActualSearchResult(), expectedSearchResult,
                String.format("Search result doesn't contain '%s'", expectedSearchResult));
    }
}
