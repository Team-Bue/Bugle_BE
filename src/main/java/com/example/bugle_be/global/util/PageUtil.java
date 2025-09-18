package com.example.bugle_be.global.util;

import com.example.bugle_be.global.exception.InvalidPaginationParameter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageUtil {
    public static final int POST_DEFAULT_PAGE_SIZE = 20;
    public static final int USER_DEFAULT_PAGE_SIZE = 10;

    public static int getTotalPageCount(Long totalCount, int limit) {
        if (totalCount == null || limit <= 0) {
            throw InvalidPaginationParameter.EXCEPTION;
        }
        return (int) Math.ceil(totalCount.doubleValue() / limit);
    }
}
