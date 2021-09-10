package com.epam.core.util;

import com.epam.core.exceptions.CustomProjectException;

import java.util.function.Supplier;

import static com.epam.core.constant.PauseConstant.PAUSE_TIME_500;

public final class WaiterUtil {

    private WaiterUtil() {
    }

    public static void waitForTrue(Supplier<Boolean> supplier, int numberOfAttempts, String errorMessage) {
        int counter = 0;
        while (counter < numberOfAttempts && !supplier.get()) {
            counter++;
            pause(PAUSE_TIME_500.getValue());
        }
        if (counter == numberOfAttempts) {
            throw new CustomProjectException(errorMessage);
        }
    }

    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new CustomProjectException("Cannot execute Thread.sleep", e);
        }
    }
}
