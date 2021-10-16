package com.epam.core.configuration.property;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:configuration.properties"
})
public interface Configuration extends Config {

    String browser();

    @Key("selenide.remote")
    String selenideRemote();

    @Key("kinopoisk.homePage")
    String homePage();

    @Key("kinopoisk.topFilms")
    int topFilms();

    @Key("kinopoisk.proxy.initialPageRef")
    String initialPageRef();

    @Key("kinopoisk.proxy.harFileName")
    String harFileName();
}
