package com.epam.core.util.reflection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@UtilityClass
@SuppressWarnings("unchecked")
public final class StepsManager {
    private static Map<String, Object> mapOfSteps;

    public static <T> T getSteps(final Class<T> clazz) {
        if (null == mapOfSteps) {
            mapOfSteps = new HashMap<>();
        }
        if (null == mapOfSteps.get(clazz.getName())) {
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
