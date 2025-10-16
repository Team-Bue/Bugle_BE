package com.example.bugle_be.domain.report.service;

import com.example.bugle_be.domain.comment.facade.CommentFacade;
import com.example.bugle_be.domain.post.facade.PostFacade;
import com.example.bugle_be.domain.report.domain.Report;
import com.example.bugle_be.domain.report.domain.ReportStatus;
import com.example.bugle_be.domain.report.domain.ReportType;
import com.example.bugle_be.domain.report.domain.repository.ReportRepository;
import com.example.bugle_be.domain.report.exception.AlreadyReported;
import com.example.bugle_be.domain.report.exception.InvalidReportType;
import com.example.bugle_be.domain.report.presentation.request.ReportRequest;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final CommentFacade commentFacade;
    private final ReportRepository reportRepository;

    @Transactional
    public void execute(Long reportedId, ReportRequest request) {
        User user = userFacade.getCurrentUser();

        switch (request.reportType()) {
            case COMMENT -> reportComment(reportedId, request.reason(), user);
            case POST -> reportPost(reportedId, request.reason(), user);
            default -> throw InvalidReportType.EXCEPTION;
        }
    }

    private void reportComment(Long commentId, String reason, User user) {
        commentFacade.validateCommentExists(commentId);
        save(commentId, reason, ReportType.COMMENT, user);
    }

    private void reportPost(Long postId, String reason, User user) {
        postFacade.validatePostExists(postId);
        save(postId, reason, ReportType.POST, user);
    }

    private void save(Long reportedId, String reason, ReportType reportType, User user) {
        if (reportRepository.existsByUserAndReportedIdAndReportType(user, reportedId, reportType)) {
            throw AlreadyReported.EXCEPTION;
        }

        reportRepository.save(
            Report.builder()
                .reportedId(reportedId)
                .reason(reason)
                .reportType(reportType)
                .reportStatus(ReportStatus.PENDING)
                .user(user)
                .build()
        );
    }
}
