package com.epam.tests.kinopoisk;

import com.epam.steps.AdvanceSearchTestSteps;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class AdvanceSearchTest {
    private static final String COUNTRY = "СССР";
    private static final List<String> GENRE_LIST;
    private static final String EXPECTED_RESULT = "Винни Пух";
    private final AdvanceSearchTestSteps testSteps;

    static {
        GENRE_LIST = List.of("детский", "мультфильм", "семейный");
    }

    public AdvanceSearchTest() {
        testSteps = new AdvanceSearchTestSteps();
    }

    @Test
    public void kinopoiskAdvanceSearchTest() {

        testSteps.openHomePage()
                .clickAdvancedSearchButton()
                .selectCountry(COUNTRY)
                .selectGenre(GENRE_LIST)
                .confirmGenre()
                .clickSearchButton()
                .validateResult(EXPECTED_RESULT);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        testSteps.close();
    }
}
