package com.epam.tests.kinopoisk;

import com.epam.dataprovider.KinopoiskTestsDataProvider;
import com.epam.steps.AdvanceSearchSteps;
import com.epam.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.core.reflection.StepManager.getSteps;

public class AdvanceSearchTest extends BaseTest {

    @Test(dataProvider = "advancedSearchTestDataProvider", dataProviderClass = KinopoiskTestsDataProvider.class)
    public void kinopoiskAdvanceSearchTest(final String country, final List<String> genreList,
                                           final String expectedSearchResult) {

        AdvanceSearchSteps steps = getSteps(AdvanceSearchSteps.class);

        steps.openHomePage()
                .clickAdvanceSearchButton()
                .searchForParticularFilm(country, genreList);

        Assert.assertEquals(steps.getActualSearchResult(), expectedSearchResult,
                String.format("Search result doesn't contain '%s'", expectedSearchResult));
    }
}
