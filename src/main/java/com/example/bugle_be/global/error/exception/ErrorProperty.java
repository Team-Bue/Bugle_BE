package com.example.bugle_be.global.error.exception;

import org.springframework.http.HttpStatus;

public interface ErrorProperty {

    HttpStatus getStatus();

    String getMessage();

}