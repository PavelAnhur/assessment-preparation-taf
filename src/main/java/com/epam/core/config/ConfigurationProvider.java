package com.epam.core.config;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

@Slf4j
@UtilityClass
public final class ConfigurationProvider {

    private static final String CONFIGURATION = "configuration";
    private static final String HOME_PAGE = "homePage";
    private static final String TOP_FILMS = "topFilms";

    public static String getHomePageUrl() {
        String homePageUrl = ResourceBundle.getBundle(CONFIGURATION).getString(HOME_PAGE);
        log.info("home page url: '{}'", homePageUrl);
        return homePageUrl;
    }

    public static int getTop() {
        String top = ResourceBundle.getBundle(CONFIGURATION).getString(TOP_FILMS);
        log.info("top films amount: '{}'", top);
        return Integer.parseInt(top);
    }
}
