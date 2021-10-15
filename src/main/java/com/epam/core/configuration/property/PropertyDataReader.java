package com.epam.core.configuration.property;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

@Slf4j
@UtilityClass
public final class PropertyDataReader {

    private static final String PROPERTY_FILE_NAME = "configuration";

    public String getPropertyValue(final String propertyName) {
        String propertyValue = ResourceBundle.getBundle(PROPERTY_FILE_NAME).getString(propertyName);
        log.info("{} property value: '{}'", propertyName, propertyValue);
        return propertyValue;
    }
}
