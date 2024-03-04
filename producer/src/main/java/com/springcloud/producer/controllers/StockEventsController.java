package com.springcloud.producer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springcloud.producer.domian.StockEvent;
import com.springcloud.producer.enums.StockEventType;
import com.springcloud.producer.errors.ErrorResponse;
import com.springcloud.producer.events.StockEventProducer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class StockEventsController {

    @Autowired
    StockEventProducer stockEventProducer;

    @PostMapping("/api/stockevent")
    public ResponseEntity<?> postStockEvent(@RequestBody @Valid StockEvent stockEvent, BindingResult result)
            throws JsonProcessingException {
        if (result.hasErrors()) {return validation(result);}
        log.info("Received StockEvent: {}", stockEvent);
        log.info("Invoking Kafka Producer: Begin");
        stockEvent.setStockEventType(StockEventType.STOCK_CREATED);
        stockEventProducer.sendStockEvent(stockEvent);
        log.info("Invoking Kafka Producer: Ends");
        return ResponseEntity.status(HttpStatus.CREATED).body(stockEvent);
    }


    private ResponseEntity<?> validation(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            err -> "El campo " + err.getField() + " " + err.getDefaultMessage()
                    ));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        } else {
            return ResponseEntity.status(HttpStatus.OK).build(); // Puedes cambiar esto a otro estado si lo deseas
        }
    }
}

