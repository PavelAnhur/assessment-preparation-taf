package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.steps.AmountOfViewsTestSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.epam.core.util.reflection.StepsManager.getSteps;

public class AmountOfViewsTest extends BaseTest {
    private static final Class<AmountOfViewsTestSteps> AMOUNT_OF_VIEWS_TEST_STEPS_CLASS = AmountOfViewsTestSteps.class;
    private static final int VIEWS_FOR_COMPARISON = 100_000;
    private static final long LOWER_BOUND_FILMS_NUMBER = 30;
    private static final long UPPER_BOUND_FILMS_NUMBER = 40;

    @Test
    public void amountOfViewsTest() {
        getSteps(AMOUNT_OF_VIEWS_TEST_STEPS_CLASS).openHomePage();
        getSteps(AMOUNT_OF_VIEWS_TEST_STEPS_CLASS).clickTop250link();

        Assertions.assertThat(getSteps(AMOUNT_OF_VIEWS_TEST_STEPS_CLASS)
                        .getFilmsNumberWithViewsLessThanCertain(VIEWS_FOR_COMPARISON))
                .withFailMessage(
                        String.format(
                                "Amount of films with less than '%d' views isn't within '%d' and '%d'",
                                VIEWS_FOR_COMPARISON,
                                LOWER_BOUND_FILMS_NUMBER,
                                UPPER_BOUND_FILMS_NUMBER
                        )
                )
                .isBetween(LOWER_BOUND_FILMS_NUMBER, UPPER_BOUND_FILMS_NUMBER);
    }
}
