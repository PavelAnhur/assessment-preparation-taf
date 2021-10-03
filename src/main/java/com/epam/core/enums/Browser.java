package com.epam.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera"),
    REMOTECHROME("remoteChrome"),
    REMOTEFIREFOX("remoteFirefox");

    private String value;
}
