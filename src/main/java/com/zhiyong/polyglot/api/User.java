package com.zhiyong.polyglot.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @NoArgsConstructor
public class User {
    @NonNull
    private String email;
    @NonNull
    private String familyName;
    @NonNull
    private String givenName;
}
