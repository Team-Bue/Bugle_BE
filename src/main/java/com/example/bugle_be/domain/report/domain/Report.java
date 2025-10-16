package com.example.bugle_be.domain.report.domain;

import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

@Getter
@Builder
@Entity(name = "tbl_report")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Report extends BaseTimeEntity {

    @Column(nullable = false)
    private Long reportedId;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(6)")
    private ReportType reportType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(8)")
    private ReportStatus reportStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
