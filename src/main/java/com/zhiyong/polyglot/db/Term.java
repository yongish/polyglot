package com.zhiyong.polyglot.db;

import lombok.Data;
import lombok.NonNull;

@Data
public class Term {
    @NonNull
    private long createdAt;
    @NonNull
    private String username;
    @NonNull
    private String term;
    @NonNull
    private int views;
    @NonNull
    private int votes;
}
