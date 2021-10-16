package com.epam.core.configuration.property;

import lombok.experimental.UtilityClass;
import org.aeonbits.owner.ConfigCache;

@UtilityClass
public class ConfigurationManager {

    public Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
