package com.example.trs.handler;



import com.example.trs.error.ApiError;
import com.example.trs.exceptions.*;
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

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    protected ResponseEntity<? extends Object> personNotFound
            ( EmployeeNotFoundException ance, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("not found", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = ActivityInThePastException.class)
    protected ResponseEntity<? extends Object> activityInThePastException
            ( ActivityInThePastException ance, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("activiteit in het verleden", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = ActivityTimeOverlapsException.class)
    protected ResponseEntity<? extends Object> activityInThePastException
            (ActivityTimeOverlapsException ance, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("Tijd overlapt met bestaande activiteit", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }


    @ExceptionHandler(value = WorkingTimeCannotStartException.class)
    protected ResponseEntity<? extends Object> workingTimeCannotBeStarted
            ( WorkingTimeCannotStartException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("bestaat al", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = WorkingTimeCannotEndException.class)
    protected ResponseEntity<? extends Object> workingTimeCannotBeEnded
            ( WorkingTimeCannotEndException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("geen uren open", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }


    @ExceptionHandler(value = WrongTypeException.class)
    protected ResponseEntity<? extends Object> wrongTypeException
            ( WrongTypeException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("wrong type", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }


}
