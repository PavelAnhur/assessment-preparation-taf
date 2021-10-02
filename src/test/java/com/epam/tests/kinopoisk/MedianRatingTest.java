package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.steps.MedianRatingSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

import static com.epam.core.util.reflection.StepManager.getSteps;

public class MedianRatingTest extends BaseTest {
    private static final int YEAR1 = 2019;
    private static final int YEAR2 = 2010;
    private final Class<MedianRatingSteps> medianRatingStepsClass = MedianRatingSteps.class;

    @Test
    public void medianRatingTest() {
        getSteps(medianRatingStepsClass)
                .openHomePage()
                .clickTop250link();

        Map<String, String> top250Map = getSteps(medianRatingStepsClass).getTop250Map();

        Assertions.assertThat(
                getSteps(medianRatingStepsClass).getAverageRatingForManufacturedYear(top250Map, YEAR1)
        ).isGreaterThan(
                getSteps(medianRatingStepsClass).getAverageRatingForManufacturedYear(top250Map, YEAR2)
        );
    }
}
