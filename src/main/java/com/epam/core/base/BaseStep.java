package com.epam.core.base;

import com.epam.core.config.ConfigurationProvider;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public abstract class BaseStep {
    private final String homePageUrl = ConfigurationProvider.getHomePageUrl();

    public void openHomePage() {
        open(homePageUrl);
        log.info("open home page '{}'", homePageUrl);
    }
}
