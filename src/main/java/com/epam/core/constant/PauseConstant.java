package com.epam.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PauseConstant {
    PAUSE_TIME_4000(4000),
    PAUSE_TIME_1000(1000),
    PAUSE_TIME_500(500),
    PAUSE_TIME_100(100);

    private int value;
}
