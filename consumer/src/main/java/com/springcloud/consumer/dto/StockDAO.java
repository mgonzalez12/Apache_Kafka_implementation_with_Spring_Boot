package com.springcloud.consumer.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class StockDAO {

    @Id
    private Integer stockId;
    private String stockName;
    private String stockDescription;
    @OneToOne
    @JoinColumn(name="stockEventId")
    private StockEventDAO stockEvent;

}
