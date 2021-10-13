package com.epam.tests.kinopoisk;

import com.epam.steps.MedianRatingSteps;
import com.epam.tests.BaseTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

import static com.epam.core.util.reflection.StepManager.getSteps;

public class MedianRatingTest extends BaseTest {
    private static final int YEAR1 = 2019;
    private static final int YEAR2 = 2010;

    @Test
    public void medianRatingTest() {
        MedianRatingSteps steps = getSteps(MedianRatingSteps.class);

        steps.openHomePage()
                .clickTop250link();

        Map<String, String> top250Map = steps.getTop250Map();

        Assertions.assertThat(
                steps.getAverageRatingForManufacturedYear(top250Map, YEAR1)
        ).isGreaterThan(
                steps.getAverageRatingForManufacturedYear(top250Map, YEAR2)
        );
    }
}
