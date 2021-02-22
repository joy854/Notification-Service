package com.joy.NotificationService.util.exceptions;

import com.joy.NotificationService.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class CustomExceptionsHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse("BAD_REQUEST",ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex,WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse("INVALID_REQUEST", ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<Object> handleForbiddenRequestException(ForbiddenException ex,WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse("Forbidden", ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TimeoutException.class)
    public final ResponseEntity<Object> handleTimeoutException(TimeoutException ex,WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse("Timeout", ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.REQUEST_TIMEOUT);
    }
}
