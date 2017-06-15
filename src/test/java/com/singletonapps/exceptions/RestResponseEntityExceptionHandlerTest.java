package com.singletonapps.exceptions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.WebRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by David Cuellar on 14/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RestResponseEntityExceptionHandlerTest {

    @InjectMocks
    private RestResponseEntityExceptionHandler exceptionHandler;

    @Mock
    private RuntimeException exception;

    @Mock
    private WebRequest request;

    @Mock
    private HttpMessageNotReadableException httpMessageNotReadableException;

    @Mock
    private HttpHeaders headers;

    @Mock
    private ExceptionMessage exceptionMessage;

    @Value("${games.app.serviceUnavailable}")
    private String serviceUnavailableMessage;

    @Value("${games.app.badRequest}")
    private String badRequest;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnServiceUnavailableWithCode503(){

        when(request.getContextPath()).thenReturn("v1/games");
        when(exceptionMessage.getServiceUnavailableMessage()).thenReturn(serviceUnavailableMessage);

        ResponseEntity<Object> response = exceptionHandler.handleServiceUnavailable(exception, request);

        assertNotNull(response);
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());

    }

    @Test
    public void shouldReturnBadRequestWithCode400(){

        when(request.getContextPath()).thenReturn("v1/games");
        when(exceptionMessage.getBadRequest()).thenReturn(badRequest);

        ResponseEntity<Object> response = exceptionHandler
                .handleHttpMessageNotReadable(httpMessageNotReadableException, headers, HttpStatus.BAD_REQUEST, request);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }
}