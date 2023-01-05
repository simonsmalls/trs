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
        ApiError err = new ApiError("persoon niet gevonden", status.value(), ance.getMessage());
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
    protected ResponseEntity<? extends Object> activityTimeOverlapsException
            (ActivityTimeOverlapsException ance, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("Tijd overlapt met bestaande activiteit", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = DateRequiredException.class)
    protected ResponseEntity<? extends Object> dateRequiredException
            (DateRequiredException dre, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("Datum vereist", status.value(), dre.getMessage());
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
        ApiError err = new ApiError("verkeerd type", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = ActivityAlreadyExistsException.class)
    protected ResponseEntity<? extends Object> ActivityAlreadyExistsException
            ( ActivityAlreadyExistsException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("activiteit bestaat al", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = ActivityDoesNotExistException.class)
    protected ResponseEntity<? extends Object> ActivityDoesNotExistException
            (ActivityDoesNotExistException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("activiteit bestaat niet", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = ActivityNotFoundException.class)
    protected ResponseEntity<? extends Object> ActivityNotFoundException
            ( ActivityNotFoundException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("activiteit bestaat niet", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = CategoryNeededException.class)
    protected ResponseEntity<? extends Object> CategoryNeededException
            ( CategoryNeededException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("categorie nodig", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = CompanyNotFoundException.class)
    protected ResponseEntity<? extends Object> CompanyNotFoundException
            ( CompanyNotFoundException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("bedrijf niet gevonden", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = CompanyAlreadyExistsException.class)
    protected ResponseEntity<? extends Object> CompanyAlreadyExistsException
            ( CompanyAlreadyExistsException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("bedrijf bestata al", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = WrongTimeException.class)
    protected ResponseEntity<? extends Object> WrongTimeException
            ( WrongTimeException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("tijd verkeerd ingegeven", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }
    @ExceptionHandler(value = EndTimeNeededException.class)
    protected ResponseEntity<? extends Object> EndTimeNeededException
            ( EndTimeNeededException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("eind tijd nodig", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = ProjectNotFoundException.class)
    protected ResponseEntity<? extends Object> ProjectNotFoundException
            ( ProjectNotFoundException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("project niet gevonden", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }
    @ExceptionHandler(value = StartTimeNeededException.class)
    protected ResponseEntity<? extends Object> StartTimeNeededException
            ( StartTimeNeededException wtexc, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("start tijd nodig", status.value(), wtexc.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }




}
