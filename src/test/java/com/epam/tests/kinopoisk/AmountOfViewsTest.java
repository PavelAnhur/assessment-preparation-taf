package com.epam.tests.kinopoisk;

import com.epam.core.base.BaseTest;
import com.epam.steps.AmountOfViewsTestSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmountOfViewsTest extends BaseTest {
    private static final int VIEWS_FOR_COMPARISON = 100_000;
    private static final int FILMS_NUMBER = 212;
    private final AmountOfViewsTestSteps testSteps;

    public AmountOfViewsTest() {
        this.testSteps = new AmountOfViewsTestSteps();
    }

    @Test
    public void amountOfViewsTest() {
        testSteps.openHomePage();
        testSteps.clickTop250link()
                .getTop250ViewsList();

        Assert.assertEquals(testSteps.getFilmsNumberWithViewsMoreThan100K(VIEWS_FOR_COMPARISON), FILMS_NUMBER,
                String.format("Number of films with views more than %d is not equal %d", VIEWS_FOR_COMPARISON, FILMS_NUMBER));
    }
}
