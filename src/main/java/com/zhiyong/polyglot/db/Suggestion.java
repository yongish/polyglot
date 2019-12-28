package com.zhiyong.polyglot.db;

import lombok.Data;
import lombok.NonNull;

@Data
public class Suggestion {
    @NonNull
    private long createdAt;
    @NonNull
    private String term;
    @NonNull
    private String content;
    @NonNull
    private String email;
    @NonNull
    private String familyName;
    @NonNull
    private String givenName;
}
