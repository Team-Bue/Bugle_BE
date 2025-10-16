package com.example.bugle_be.domain.report.domain.repository;

import com.example.bugle_be.domain.report.domain.Report;
import com.example.bugle_be.domain.report.domain.ReportType;
import com.example.bugle_be.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    boolean existsByUserAndReportedIdAndReportType(User user, Long reportedId, ReportType reportType);
}
