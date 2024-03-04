package com.springcloud.consumer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloud.consumer.dto.StockEventDAO;
import com.springcloud.consumer.repository.StockEventsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class StockEventsService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    private StockEventsRepository stockEventsRepository;

    @Transactional
    public void processStockEvents(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
        StockEventDAO stockEvent = objectMapper.readValue(consumerRecord.value(), StockEventDAO.class);
        log.info("StockEvent recibido: {}", stockEvent);

        if (stockEvent.getStockEventId() != null && stockEvent.getStockEventId() == 000) {
            throw new RecoverableDataAccessException("Problema de red temporal");
        }

        switch (stockEvent.getStockEventType()) {
            case STOCK_CREATED:
                log.info("Crear en tablas");
                save(stockEvent);
                break;
            default:
                log.info("Tipo de evento de stock no válido");
        }
    }

    @Transactional
    private void save(StockEventDAO stockEventDAO) {
        log.info("Guardando StockEvent: {}", stockEventDAO);
        stockEventDAO.getStock().setStockEvent(stockEventDAO);
        stockEventsRepository.save(stockEventDAO);
        log.info("StockEvent se guardó correctamente: {}", stockEventDAO);
    }
}

