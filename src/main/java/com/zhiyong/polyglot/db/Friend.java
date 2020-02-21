package com.zhiyong.polyglot.db;

import lombok.Data;
import lombok.NonNull;

@Data
public class Friend {
    @NonNull
    private String userUid;
    @NonNull
    private String userEmail;
    @NonNull
    private String friendUid;
    @NonNull
    private String friendEmail;
    @NonNull
    private String friendName;
}
