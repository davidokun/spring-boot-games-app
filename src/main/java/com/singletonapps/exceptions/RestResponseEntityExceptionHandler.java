package com.singletonapps.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by David Cuellar on 8/06/17.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ExceptionMessage exceptionMessage;


    @ExceptionHandler(value = { DataAccessResourceFailureException.class, UncategorizedMongoDbException.class })
    protected ResponseEntity<Object> handleServiceUnavailable(RuntimeException ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String bodyOfResponse = String.format(exceptionMessage.getServiceUnavailableMessage(), request.getContextPath());

        return handleExceptionInternal(ex, String.format(bodyOfResponse, HttpStatus.SERVICE_UNAVAILABLE),
                headers, HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        headers.setContentType(MediaType.APPLICATION_JSON);

        String bodyOfResponse = String.format(exceptionMessage.getBadRequest(), request.getContextPath());

        return handleExceptionInternal(ex, String.format(bodyOfResponse, HttpStatus.BAD_REQUEST),
                headers, HttpStatus.BAD_REQUEST, request);
    }
}
