package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.dataprovider.KinopoiskTestsDataProvider;
import com.epam.steps.AdvanceSearchTestSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.core.util.reflection.StepsManager.getSteps;

public class AdvanceSearchTest extends BaseTest {
    private static final Class<AdvanceSearchTestSteps> ADVANCE_SEARCH_TEST_STEPS_CLASS = AdvanceSearchTestSteps.class;

    @Test(dataProvider = "advancedSearchTestDataProvider", dataProviderClass = KinopoiskTestsDataProvider.class)
    public void kinopoiskAdvanceSearchTest(String country, List<String> genreList, String expectedSearchResult) {

        getSteps(ADVANCE_SEARCH_TEST_STEPS_CLASS)
                .openHomePage();
        getSteps(ADVANCE_SEARCH_TEST_STEPS_CLASS)
                .clickAdvancedSearchButton()
                .selectCountry(country)
                .selectGenre(genreList)
                .confirmGenre()
                .clickSearchButton();

        Assert.assertEquals(getSteps(ADVANCE_SEARCH_TEST_STEPS_CLASS).getActualSearchResult(), expectedSearchResult,
                String.format("Search result doesn't contain '%s'", expectedSearchResult));
    }
}
