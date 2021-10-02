package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.steps.AmountOfViewsSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.epam.core.util.reflection.StepManager.getSteps;

public class AmountOfViewsTest extends BaseTest {
    private static final int VIEWS_FOR_COMPARISON = 100_000;
    private static final long LOWER_BOUND_FILMS_NUMBER = 30;
    private static final long UPPER_BOUND_FILMS_NUMBER = 40;
    private final Class<AmountOfViewsSteps> amountOfViewsStepsClass = AmountOfViewsSteps.class;

    @Test
    public void amountOfViewsTest() {
        getSteps(amountOfViewsStepsClass)
                .openHomePage()
                .clickTop250link();

        Assertions.assertThat(getSteps(amountOfViewsStepsClass)
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
