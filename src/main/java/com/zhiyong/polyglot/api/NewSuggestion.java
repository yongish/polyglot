package com.zhiyong.polyglot.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor
public class NewSuggestion {
    @NonNull
    private String content;
    @NonNull
    private String familyName;
    @NonNull
    private String givenName;
}
