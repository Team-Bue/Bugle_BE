package com.example.bugle_be.infra.elasticsearch.dlq;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DlqConstants {

    // Redis DLQ 키
    public static final String POST_DLQ_KEY = "es:dlq:post";
    public static final String USER_DLQ_KEY = "es:dlq:user";

    // DLQ 스케줄러 사이클 기준 최대 재처리 횟수 (30분 간격)
    public static final int MAX_DLQ_RETRY_COUNT = 3;

    // Listener 즉시 재시도 횟수 (5s → 30s → 120s)
    public static final int MAX_RETRY_ATTEMPTS = 3;
    public static final long RETRY_INITIAL_INTERVAL = 5_000L;
    public static final long RETRY_MAX_INTERVAL = 120_000L;
    public static final double RETRY_MULTIPLIER = 6.0;
}

