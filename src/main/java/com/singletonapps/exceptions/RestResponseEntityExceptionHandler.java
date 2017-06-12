package com.singletonapps.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by David Cuellar on 8/06/17.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${games.app.serviceUnavailable}")
    private String serviceUnavailableMessage;


    @ExceptionHandler(value = { DataAccessResourceFailureException.class })
    protected ResponseEntity<Object> handleServiceUnavailable(RuntimeException ex, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final String bodyOfResponse = String.format(serviceUnavailableMessage, request.getContextPath());

        return handleExceptionInternal(ex, String.format(bodyOfResponse, HttpStatus.SERVICE_UNAVAILABLE),
                headers, HttpStatus.SERVICE_UNAVAILABLE, request);
    }
}
