package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.steps.AmountOfViewsTestSteps;
import com.epam.steps.MedianRatingTestSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

import static com.epam.core.util.reflection.StepsManager.getSteps;

public class MedianRatingTest extends BaseTest {
    private static final int YEAR1 = 2019;
    private static final int YEAR2 = 2010;

    @Test
    public void medianRatingTest() {
        getSteps(AmountOfViewsTestSteps.class)
                .openHomePage();
        getSteps(AmountOfViewsTestSteps.class)
                .clickTop250link();

        Map<String, String> top250Map = getSteps(MedianRatingTestSteps.class).getTop250Map();

        Assertions.assertThat(getSteps(MedianRatingTestSteps.class).getAverageRatingForManufacturedYear(top250Map, YEAR1))
                .isGreaterThan(getSteps(MedianRatingTestSteps.class).getAverageRatingForManufacturedYear(top250Map, YEAR2));
    }
}
