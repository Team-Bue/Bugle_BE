package com.example.bugle_be.domain.report.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportStatus {
    PENDING("신고 접수"),
    RESOLVED("처리 완료"),
    REJECTED("신고 거절됨");

    private final String status;
}
