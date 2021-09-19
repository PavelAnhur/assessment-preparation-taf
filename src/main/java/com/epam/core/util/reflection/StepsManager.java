package com.epam.core.util.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SuppressWarnings("unchecked")
public final class StepsManager {
    private static Map<String, Object> mapOfSteps;

    private StepsManager() {
    }

    public static <T> T getSteps(final Class<T> clazz) {
        if (mapOfSteps == null) {
            mapOfSteps = new HashMap<>();
        }
        if (mapOfSteps.get(clazz.getName()) == null) {
            mapOfSteps.put(clazz.getName(), createStepObject(clazz));
        }
        return (T) mapOfSteps.get(clazz.getName());
    }

    private static <T> T createStepObject(final Class<T> clazz) {
        T stepObject = null;
        try {
            stepObject = clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
                | NoSuchMethodException | InvocationTargetException e) {
            log.error(e.getMessage());
        }
        return stepObject;
    }
}
