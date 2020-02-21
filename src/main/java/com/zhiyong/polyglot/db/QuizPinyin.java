package com.zhiyong.polyglot.db;

import lombok.Data;
import lombok.NonNull;

@Data
public class QuizPinyin {
    @NonNull
    private Long quizId;
    @NonNull
    private String pinyinString;
    @NonNull
    private String wordString;
}
