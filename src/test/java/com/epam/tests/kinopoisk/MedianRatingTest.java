package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.steps.AmountOfViewsTestSteps;
import com.epam.steps.MedianRatingTestSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

public class MedianRatingTest extends BaseTest {
    private static final int YEAR1 = 2019;
    private static final int YEAR2 = 2010;
    private final AmountOfViewsTestSteps amountOfViewsTestSteps;
    private final MedianRatingTestSteps medianRatingTestSteps;

    public MedianRatingTest() {
        amountOfViewsTestSteps = new AmountOfViewsTestSteps();
        medianRatingTestSteps = new MedianRatingTestSteps();
    }

    @Test
    public void medianRatingTest() {
        amountOfViewsTestSteps
                .openHomePage();
        amountOfViewsTestSteps
                .clickTop250link();

        Map<String, String> top250Map = medianRatingTestSteps.getTop250Map();

        Assertions.assertThat(medianRatingTestSteps.getAverageRatingForManufacturedYear(top250Map, YEAR1))
                .isGreaterThan(medianRatingTestSteps.getAverageRatingForManufacturedYear(top250Map, YEAR2));
    }
}
