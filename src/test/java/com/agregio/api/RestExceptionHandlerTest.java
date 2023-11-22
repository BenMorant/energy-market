package com.agregio.api;

import com.agregio.exception.MarketTypeException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestExceptionHandlerTest {

    @Test
    void testHandleMarketTypeException() {
        RestExceptionHandler handler = new RestExceptionHandler();
        MarketTypeException exception = new MarketTypeException("toto");
        ResponseEntity<Object> response = handler.handleMarketTypeException(exception);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

}