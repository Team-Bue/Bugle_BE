package com.example.bugle_be.domain.report.presentation.request;

import com.example.bugle_be.domain.report.domain.ReportType;
import com.example.bugle_be.global.util.MessageProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReportRequest(

    @NotBlank(message = MessageProperty.REPORT_REASON_NOT_BLANK)
    @Size(max = 100, message = MessageProperty.REPORT_REASON_SIZE)
    String reason,

    @NotNull(message = MessageProperty.REPORT_TYPE_NOT_NULL)
    ReportType reportType
) {
}
