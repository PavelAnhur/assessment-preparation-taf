package com.epam.core.utility;

import com.epam.core.exceptions.KinopoiskProjectException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

import static com.epam.core.enums.PauseConstant.PAUSE_TIME_1000;

@UtilityClass
public final class WaitUtil {

    public void waitForTrue(final Supplier<Boolean> supplier, final int numberOfAttempts, final String errorMessage) {
        int counter = 0;
        while (counter < numberOfAttempts && !supplier.get()) {
            counter++;
            pause(PAUSE_TIME_1000.getValue());
        }
        if (counter == numberOfAttempts) {
            throw new KinopoiskProjectException(errorMessage);
        }
    }

    public void pause(final int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new KinopoiskProjectException("Cannot execute Thread.sleep", e);
        }
    }
}
