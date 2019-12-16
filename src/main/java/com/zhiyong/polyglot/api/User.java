package com.zhiyong.polyglot.api;

import lombok.Data;
import lombok.NonNull;

import javax.ws.rs.QueryParam;

@Data
public class User {
    @NonNull @QueryParam("familyName")
    private final String familyName;
    @NonNull
    private final String givenName;
}
