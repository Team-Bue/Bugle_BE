package com.example.bugle_be.domain.report.presentation;

import com.example.bugle_be.domain.report.presentation.request.ReportRequest;
import com.example.bugle_be.domain.report.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/{reported-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void report(@PathVariable("reported-id") Long reportedId, @RequestBody @Valid ReportRequest request) {
        reportService.execute(reportedId, request);
    }
}
