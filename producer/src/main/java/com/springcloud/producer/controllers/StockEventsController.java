package com.springcloud.producer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springcloud.producer.domian.StockEvent;
import com.springcloud.producer.enums.StockEventType;
import com.springcloud.producer.events.StockEventProducer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StockEventsController {

    @Autowired
    StockEventProducer stockEventProducer;

    @PostMapping("/api/stockevent")
    public ResponseEntity<StockEvent> postStockEvent(@RequestBody @Valid StockEvent stockEvent)
    throws JsonProcessingException {
        log.info("Invoking Kafka Producer: Begin");
        stockEvent.setStockEventType(StockEventType.STOCK_CREATED);
        stockEventProducer.sendStockEvent(stockEvent);
        log.info("Invoking Kafka Producer: Ends");
        return ResponseEntity.status(HttpStatus.CREATED).body(stockEvent);
    }
}
