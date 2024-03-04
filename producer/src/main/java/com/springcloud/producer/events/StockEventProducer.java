package com.springcloud.producer.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.producer.domian.StockEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public class StockEventProducer {

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendStockEvent(StockEvent stockEvent) throws JsonProcessingException {
        Integer key = stockEvent.getStockEventId();
        String value = objectMapper.writeValueAsString(stockEvent);

        // Use CompletableFuture instead of ListenableFuture
        CompletableFuture<SendResult<Integer, String>> future = kafkaTemplate.sendDefault(key, value);

        // Add callbacks for handling success and failure
        future.whenComplete((result, throwable) -> {
            if (throwable != null) {
                handleFailure(key, value, throwable);
            } else {
                // Handle success, e.g., log the message or offset
            }
        });
    }

    // Implement the handleFailure method
    private void handleFailure(Integer key, String value, Throwable throwable) {
        // Handle the failure appropriately, e.g., log the error, retry, or notify a monitoring system
        System.out.println("Error sending message: " + throwable.getMessage());
        // Add your specific error handling logic here
    }
}

