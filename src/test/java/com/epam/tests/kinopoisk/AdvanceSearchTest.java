package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.dataprovider.KinopoiskTestsDataProvider;
import com.epam.steps.AdvanceSearchTestSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.core.util.reflection.StepsManager.getSteps;

public class AdvanceSearchTest extends BaseTest {

    @Test(dataProvider = "advancedSearchTestDataProvider", dataProviderClass = KinopoiskTestsDataProvider.class)
    public void kinopoiskAdvanceSearchTest(String country, List<String> genreList, String expectedSearchResult) {

        getSteps(AdvanceSearchTestSteps.class).openHomePage();
        getSteps(AdvanceSearchTestSteps.class).clickAdvancedSearchButton()
                .selectCountry(country)
                .selectGenre(genreList)
                .confirmGenre()
                .clickSearchButton();

        Assert.assertEquals(getSteps(AdvanceSearchTestSteps.class).getActualSearchResult(), expectedSearchResult,
                String.format("Search result doesn't contain '%s'", expectedSearchResult));
    }
}
