package com.springcloud.consumer.repository;

import com.springcloud.consumer.dto.StockEventDAO;
import org.springframework.data.repository.CrudRepository;

public interface StockEventsRepository extends CrudRepository<StockEventDAO, Integer> {
}
