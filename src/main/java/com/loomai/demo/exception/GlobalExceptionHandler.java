package com.loomai.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handle(NotFoundException ex) {

        return new ResponseEntity<>(getError(HttpStatus.NOT_FOUND, ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handle(Exception ex, WebRequest request) {
        try {
            return super.handleException(ex, request);
        } catch (Exception e) {
            return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getError(status, ex), headers, status);
    }

    private RequestError getError(HttpStatus status, Exception ex) {
        return RequestError.builder()
                .status(status.value())
                .message(ex.getMessage())
                .build();
    }

}
