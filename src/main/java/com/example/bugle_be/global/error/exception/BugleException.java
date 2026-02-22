package com.example.bugle_be.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BugleException extends RuntimeException {

    private final ErrorProperty errorProperty;
}
