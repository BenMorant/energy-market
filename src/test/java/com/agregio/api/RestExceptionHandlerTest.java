package com.agregio.api;

import com.agregio.exception.HourlyBlockException;
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

    @Test
    void testHandleHourlyBlockException() {
        RestExceptionHandler handler = new RestExceptionHandler();
        HourlyBlockException exception = new HourlyBlockException("toto");
        ResponseEntity<Object> response = handler.handleHourlyBlockException(exception);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

}