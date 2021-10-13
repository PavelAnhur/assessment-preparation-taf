package com.epam.core.config;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

@Slf4j
@UtilityClass
public final class PropertyDataReader {

    private static final String CONFIGURATION = "configuration";

    public String getPropertyValue(final String propertyName) {
        String propertyValue = ResourceBundle.getBundle(CONFIGURATION).getString(propertyName);
        log.info("{} property value: '{}'", propertyName, propertyValue);
        return propertyValue;
    }
}
