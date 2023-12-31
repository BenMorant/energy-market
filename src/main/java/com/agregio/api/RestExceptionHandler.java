package com.agregio.api;

import com.agregio.exception.FarmTypeException;
import com.agregio.exception.HourlyBlockException;
import com.agregio.exception.MarketTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MarketTypeException.class)
    public ResponseEntity<Object> handleMarketTypeException(
            MarketTypeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HourlyBlockException.class)
    public ResponseEntity<Object> handleHourlyBlockException(
            HourlyBlockException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FarmTypeException.class)
    public ResponseEntity<Object> handleFarmTypeException(
            FarmTypeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}