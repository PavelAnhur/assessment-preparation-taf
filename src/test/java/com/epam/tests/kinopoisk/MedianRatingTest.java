package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.steps.AmountOfViewsTestSteps;
import com.epam.steps.MedianRatingTestSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

import static com.epam.core.util.reflection.StepsManager.getSteps;

public class MedianRatingTest extends BaseTest {
    private static final Class<AmountOfViewsTestSteps> AMOUNT_OF_VIEWS_TEST_STEPS_CLASS = AmountOfViewsTestSteps.class;
    private static final Class<MedianRatingTestSteps> MEDIAN_RATING_TEST_STEPS_CLASS = MedianRatingTestSteps.class;
    private static final int YEAR1 = 2019;
    private static final int YEAR2 = 2010;

    @Test
    public void medianRatingTest() {
        getSteps(AMOUNT_OF_VIEWS_TEST_STEPS_CLASS)
                .openHomePage();
        getSteps(AMOUNT_OF_VIEWS_TEST_STEPS_CLASS)
                .clickTop250link();

        Map<String, String> top250Map = getSteps(MEDIAN_RATING_TEST_STEPS_CLASS).getTop250Map();

        Assertions.assertThat(getSteps(MEDIAN_RATING_TEST_STEPS_CLASS).getAverageRatingForManufacturedYear(top250Map, YEAR1))
                .isGreaterThan(getSteps(MEDIAN_RATING_TEST_STEPS_CLASS).getAverageRatingForManufacturedYear(top250Map, YEAR2));
    }
}
