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
    private final Class<AmountOfViewsTestSteps> amountOfViewsTestStepsClass = AmountOfViewsTestSteps.class;
    private final Class<MedianRatingTestSteps> medianRatingTestStepsClass = MedianRatingTestSteps.class;

    @Test
    public void medianRatingTest() {
        getSteps(amountOfViewsTestStepsClass)
                .openHomePage();
        getSteps(amountOfViewsTestStepsClass)
                .clickTop250link();

        Map<String, String> top250Map = getSteps(medianRatingTestStepsClass).getTop250Map();

        Assertions.assertThat(getSteps(medianRatingTestStepsClass).getAverageRatingForManufacturedYear(top250Map, YEAR1))
                .isGreaterThan(getSteps(medianRatingTestStepsClass).getAverageRatingForManufacturedYear(top250Map, YEAR2));
    }
}
