package com.zhiyong.polyglot.db;

import lombok.Data;
import lombok.NonNull;

@Data
public class Term {
    @NonNull
    private long createdAt;
    @NonNull
    private String userId;
    @NonNull
    private String familyName;
    @NonNull
    private String givenName;
    @NonNull
    private String term;
    @NonNull
    private int views;
    @NonNull
    private int upvotes;
    @NonNull
    private int downvotes;
}
