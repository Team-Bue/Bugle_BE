package com.example.bugle_be.global.error;

import com.example.bugle_be.global.error.exception.BugleException;
import com.example.bugle_be.global.error.exception.ErrorProperty;
import com.example.bugle_be.global.error.exception.GlobalErrorCode;
import com.example.bugle_be.global.error.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BugleException e) {
            log.error("BugleException 발생 !!", e);
            writeErrorResponse(response, e.getErrorProperty());
        } catch (Exception e) {
            if (e.getCause() instanceof BugleException bugleException) {
                writeErrorResponse(response, bugleException.getErrorProperty());
            } else {
                log.error("예상치 못한 에러 발생!!", e);
                writeErrorResponse(response, GlobalErrorCode.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void writeErrorResponse(HttpServletResponse response, ErrorProperty errorProperty) throws IOException {
        response.setStatus(errorProperty.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        ErrorResponse errorResponse = ErrorResponse.of(errorProperty);
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
