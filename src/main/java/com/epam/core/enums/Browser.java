package com.epam.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera");

    private String value;
}
