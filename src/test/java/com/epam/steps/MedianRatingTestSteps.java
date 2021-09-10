package com.epam.steps;

import com.epam.core.base.BaseStep;
import com.epam.core.exceptions.CustomProjectException;
import com.epam.core.util.service.WebElementService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class MedianRatingTestSteps extends BaseStep {

    public Map<String, String> getTop250Map() {
        return WebElementService.getMapOfElementText("original-name", "rating__value");
    }

    public double getAverageRatingForManufacturedYear(final Map<String, String> top250Map, final int year) {
        List<String> ratingList = new ArrayList<>();

        top250Map.entrySet()
                .stream()
                .filter(entryKey -> entryKey.getKey().endsWith(String.valueOf(year)))
                .forEach(entry -> {
                    ratingList.add(entry.getValue());
                    log.info("Name = '{}', Rating = '{}'", entry.getKey(), entry.getValue());
                });

        double averageRating = ratingList.stream()
                .mapToDouble(Double::parseDouble)
                .average()
                .stream()
                .findFirst()
                .orElseThrow(() -> new CustomProjectException("average rating calculation failed"));

        log.info("average rating for '{}' manufactured year: '{}'", year, averageRating);

        return averageRating;
    }
}
