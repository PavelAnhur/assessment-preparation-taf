package com.epam.dataprovider;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class KinopoiskTestsDataProvider {

    private KinopoiskTestsDataProvider() {
    }

    @DataProvider
    public static Iterator<Object[]> advancedSearchTestDataProvider() {
        Collection<Object[]> dp = new ArrayList<>();

        dp.add(new Object[]{
                "СССР",
                List.of("детский", "мультфильм", "семейный"),
                "Винни Пух"
        });

        dp.add(new Object[]{
                "США",
                List.of("боевик", "приключения", "фантастика"),
                "Светлячок (сериал)"
        });

        dp.add(new Object[]{
                "Канада",
                List.of("драма", "приключения", "фантастика"),
                "Звёздная принцесса и силы зла (сериал)"
        });
        return dp.iterator();
    }
}
