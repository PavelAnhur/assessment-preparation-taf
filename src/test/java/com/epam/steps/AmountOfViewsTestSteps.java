package com.epam.steps;

import com.epam.core.base.BaseStep;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class AmountOfViewsTestSteps extends BaseStep {
    private static final int TOP = 250;
    private final List<String> top250ViewsList;

    public AmountOfViewsTestSteps() {
        this.top250ViewsList = new ArrayList<>();
    }

    public AmountOfViewsTestSteps clickTop250link() {
        log.info("click on top{} link", TOP);
        $(By.xpath("//*[contains(@class,'middle')]//*[@href='/lists/top250/']")).click();
        return this;
    }

    public void getTop250ViewsList() {
        for (int i = 1; i <= TOP; i++) {
            top250ViewsList.add(
                    $(By.xpath(String.format(
                            "//*[@class='film-item-rating-position__position' and text()='%s']/../../..//*[@class='rating__count']",
                            i))
                    ).getText());
            if ((i % 50 == 0) && i < TOP) {
                $(By.xpath("//*[text()='Вперёд']")).click();
            }
        }

        log.info("top {} rating list: {}", TOP, top250ViewsList);
    }

    public long getFilmsNumberWithViewsMoreThan100K(final int viewsForComparison) {
        long amount = top250ViewsList.stream()
                .map(votes -> votes.replace(" ", ""))
                .collect(Collectors.toList())
                .stream()
                .filter(votes -> Integer.parseInt(votes) > viewsForComparison)
                .count();

        log.info("amount of films with views more than {} -> '{}'", viewsForComparison, amount);
        return amount;
    }
}
