package com.example.trs.handler;


import com.example.trs.error.ApiError;
import com.example.trs.exceptions.ActivityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ActivityNotFoundException.class)
    protected ResponseEntity<? extends Object> handleActivityNotFound(ActivityNotFoundException activityNotFoundException, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError error = new ApiError("Activity not found", status.value(), activityNotFoundException.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(error, responseHeaders, status);
    }
}
