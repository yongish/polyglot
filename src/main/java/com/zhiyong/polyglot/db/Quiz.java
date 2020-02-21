package com.zhiyong.polyglot.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Quiz {
    @NonNull
    private String userUid;
    @NonNull
    private int quizId;
    @NonNull
    private String email;
    @NonNull
    private int date;
    @NonNull
    private String title;
    @NonNull
    private int totalWords;
    @NonNull
    private int notLearned;
    @NonNull
    private int round;
}
