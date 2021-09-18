package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.dataprovider.KinopoiskTestsDataProvider;
import com.epam.steps.AdvanceSearchTestSteps;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.List;

public class AdvanceSearchTest extends BaseTest {
    private final AdvanceSearchTestSteps testSteps;
    private final String country;
    private final List<String> genreList;
    private final String expectedSearchResult;

    @Factory(dataProvider = "advancedSearchTestDataProvider", dataProviderClass = KinopoiskTestsDataProvider.class)
    public AdvanceSearchTest(String country, List<String> genreList, String expectedSearchResult) {
        this.country = country;
        this.genreList = genreList;
        this.expectedSearchResult = expectedSearchResult;
        testSteps = new AdvanceSearchTestSteps();
    }

    @Test(singleThreaded = true)
    public void kinopoiskAdvanceSearchTest() {
        testSteps.openHomePage();
        testSteps.clickAdvancedSearchButton()
                .selectCountry(country)
                .selectGenre(genreList)
                .confirmGenre()
                .clickSearchButton();

        Assert.assertEquals(testSteps.getActualSearchResult(), expectedSearchResult,
                String.format("Search result doesn't contain '%s'", expectedSearchResult));
    }
}
