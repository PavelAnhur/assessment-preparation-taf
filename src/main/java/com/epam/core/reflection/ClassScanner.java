package com.epam.core.reflection;

import com.epam.core.annotation.Proxy;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public final class ClassScanner {

    public static final String TESTS_PACKAGE = "com.epam.tests.kinopoisk";
    public static final String REGEX = "^.*void (.*Test)\\..*?$";

    public boolean isAnnotatedWithProxy() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(TESTS_PACKAGE))
                .setScanners(new MethodAnnotationsScanner()));
        Set<Method> methodSet = reflections.getMethodsAnnotatedWith(Proxy.class);

        List<String> methodNameList = new ArrayList<>();
        log.info("classes with @Proxy annotation:\n");
        methodSet.forEach(entry -> {
            log.info("entry value: '{}'", entry);
            addClassNameToList(methodNameList, entry);
        });
        log.info("method names list:\n");
        methodNameList.forEach(log::info);

        List<String> currentThreadClassList = getCurrentThreadClassList();

        return compareMethodNameAndThreadClassName(methodNameList, currentThreadClassList);
    }

    private void addClassNameToList(final List<String> classNameList, final Method method) {
        log.info("method as generic string: '{}'", method.toGenericString());
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(method.toGenericString());
        if (matcher.find()) {
            classNameList.add(matcher.group(1).trim());
        }
    }

    private List<String> getCurrentThreadClassList() {
        log.info("current thread classes:\n");
        return Arrays.stream(Thread.currentThread().getStackTrace())
                .map(StackTraceElement::toString)
                .peek(log::info)
                .collect(Collectors.toList());
    }

    private boolean compareMethodNameAndThreadClassName(final List<String> methodNameList,
                                                        final List<String> currentThreadClassList) {

        AtomicBoolean isConsistentNameExist = new AtomicBoolean(false);
        currentThreadClassList.forEach(element -> {
            for (String method : methodNameList) {
                if (element.startsWith(method)) {
                    isConsistentNameExist.set(true);
                }
            }
        });
        log.info("consistent names exist: '{}'\n", isConsistentNameExist.get());
        return isConsistentNameExist.get();
    }
}
