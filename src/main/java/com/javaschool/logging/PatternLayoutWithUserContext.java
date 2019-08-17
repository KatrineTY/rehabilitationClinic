package com.javaschool.logging;

import ch.qos.logback.classic.PatternLayout;

public class PatternLayoutWithUserContext extends PatternLayout {
    static {
        PatternLayout.defaultConverterMap.put(
                "user", UserConverter.class.getName());
    }
}
