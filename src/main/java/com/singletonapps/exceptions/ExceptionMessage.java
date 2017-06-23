package com.singletonapps.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by David Cuellar on 14/06/17.
 */
@Component
class ExceptionMessage {

    @Value("${games.app.serviceUnavailable}")
    private String serviceUnavailableMessage;

    @Value("${games.app.badRequest}")
    private String badRequest;

    @Value("${games.app.notFound}")
    private String notFound;

    String getServiceUnavailableMessage() {
        return serviceUnavailableMessage;
    }

    String getBadRequest() {
        return badRequest;
    }

    public String getNotFound() {
        return notFound;
    }
}
