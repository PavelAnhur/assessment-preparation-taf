package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.dataprovider.KinopoiskTestsDataProvider;
import com.epam.steps.AdvanceSearchTestSteps;
import org.testng.annotations.Test;

import java.util.List;

public class AdvanceSearchTest extends BaseTest {
    private final AdvanceSearchTestSteps testSteps;

    public AdvanceSearchTest() {
        testSteps = new AdvanceSearchTestSteps();
    }

    @Test(dataProvider = "advancedSearchTestDataProvider", dataProviderClass = KinopoiskTestsDataProvider.class)
    public void kinopoiskAdvanceSearchTest(String country, List<String> genreList, String expectedResult) {

        testSteps.openHomePage();
        testSteps.clickAdvancedSearchButton()
                .selectCountry(country)
                .selectGenre(genreList)
                .confirmGenre()
                .clickSearchButton()
                .validateResult(expectedResult);
    }
}
