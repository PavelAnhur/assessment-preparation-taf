package com.epam.core.util.reflection;

import com.epam.core.annotation.Proxy;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.Set;

@Slf4j
@UtilityClass
public final class ClassScanner {

    public static boolean isAnnotatedWithProxy() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.epam.tests.kinopoisk"))
                .setScanners(new MethodAnnotationsScanner()));
        Set<Method> classes = reflections.getMethodsAnnotatedWith(Proxy.class);
        return !classes.isEmpty();
    }
}
