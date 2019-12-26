package com.zhiyong.polyglot.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor
public class User {
    @NonNull
    private String userId;
    @NonNull
    private String familyName;
    @NonNull
    private String givenName;
}
