package com.epam.steps;

import com.epam.core.base.BaseStep;
import com.epam.core.config.ConfigurationProvider;
import com.epam.core.util.service.WebElementService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class AmountOfViewsTestSteps extends BaseStep {

    public void clickTop250link() {
        log.info("click on top{} link", ConfigurationProvider.getTop());
        $(By.xpath("//*[contains(@class,'middle')]//*[@href='/lists/top250/']")).click();
    }

    public long getFilmsNumberWithViewsLessThanCertain(final int viewsForComparison) {
        long amount = WebElementService.getMapOfElementText("original-name", "rating__count")
                .values()
                .stream()
                .map(votes -> votes.replace(" ", ""))
                .collect(Collectors.toList())
                .stream()
                .filter(votes -> Integer.parseInt(votes) < viewsForComparison)
                .peek(log::info)
                .count();

        log.info("amount of films with views less than {} -> '{}'", viewsForComparison, amount);
        return amount;
    }
}
