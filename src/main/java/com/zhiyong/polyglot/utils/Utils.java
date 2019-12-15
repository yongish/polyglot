package com.zhiyong.polyglot.utils;

import java.time.Instant;

public class Utils {
    public static int getEpochSecond() {
        return Math.toIntExact(Instant.now().getEpochSecond());
    }
}
