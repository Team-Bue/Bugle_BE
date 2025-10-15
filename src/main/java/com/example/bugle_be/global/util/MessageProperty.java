package com.example.bugle_be.global.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageProperty {
    // loginId
    public static final String LOGIN_ID_NOT_BLANK = "이메일 또는 아이디는 필수 입력 항목입니다.";

    // email
    public static final String EMAIL_INVALID = "올바른 형식의 이메일을 입력해주세요.";
    public static final String EMAIL_NOT_BLANK = "이메일은 필수 입력 항목입니다.";

    // accountId
    public static final String ACCOUNT_ID_PATTERN = "아이디는 소문자, 숫자, 언더바(_), 점(.)만 사용 가능하며, 시작과 끝은 점(.)일 수 없습니다.";
    public static final String ACCOUNT_ID_NOT_BLANK = "아이디는 필수 입력 항목입니다.";
    public static final String ACCOUNT_ID_SIZE = "아이디는 최소 4자 이상, 20자 이하로 입력해주세요.";

    // password
    public static final String PASSWORD_PATTERN = "비밀번호는 영어 대소문자, 숫자만 허용되며 @, #, !, %, &, * 중 하나 이상을 포함해야 합니다.";
    public static final String PASSWORD_NOT_BLANK = "비밀번호는 필수 입력 항목입니다.";
    public static final String PASSWORD_SIZE = "비밀번호는 최소 8자 이상, 30자 이하로 입력해주세요.";

    // userName
    public static final String USERNAME_PATTERN = "이름은 한글 또는 영어 대소문자만 입력 가능합니다.";
    public static final String USERNAME_SIZE = "이름은 20자 이내로 입력해주세요.";

    // token
    public static final String TOKEN_NOT_BLANK = "인증 토큰은 필수 입력 항목입니다.";

    // comment
    public static final String COMMENT_NOT_BLANK = "댓글은 필수 입력 항목입니다.";
    public static final String COMMENT_SIZE = "댓글은 300자 이내로 입력해주세요.";
}
