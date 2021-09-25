package com.epam.core.enums;

import lombok.Getter;

@Getter
public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera"),
    REMOTE_CHROME("remoteChrome"),
    REMOTE_FIREFOX("remoteFirefox"),
    REMOTE_OPERA("remoteOpera");

    private final String value;

    Browser(String value) {
        this.value = value;
    }
}
