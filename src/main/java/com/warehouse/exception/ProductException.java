package com.warehouse.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

public class ProductException extends WebApplicationException {
    public ProductException(ExceptionMessage message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(message)
                .type(APPLICATION_JSON_TYPE)
                .build());
    }
}