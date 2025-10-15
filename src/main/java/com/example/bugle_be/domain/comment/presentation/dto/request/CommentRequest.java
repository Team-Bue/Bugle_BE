package com.example.bugle_be.domain.comment.presentation.dto.request;

import com.example.bugle_be.global.util.MessageProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequest(
    @NotBlank(message = MessageProperty.COMMENT_NOT_BLANK)
    @Size(max = 300, message = MessageProperty.COMMENT_SIZE)
    String content
) {
}
