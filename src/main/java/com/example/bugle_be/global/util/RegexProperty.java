package com.example.bugle_be.global.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexProperty {

    public static final String PASSWORD = "^(?=.*[@#!%&*])[a-zA-Z0-9@#!%&*]+$";

    public static final String ACCOUNT_ID = "^(?!\\.)([a-z0-9._]{4,20})(?<!\\.)$";

    public static final String USERNAME = "^[a-zA-Z가-힣]+$";
}
