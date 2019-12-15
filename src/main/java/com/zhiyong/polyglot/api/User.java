package com.zhiyong.polyglot.api;

import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    @NonNull
    private final String familyName;
    @NonNull
    private final String givenName;
}
