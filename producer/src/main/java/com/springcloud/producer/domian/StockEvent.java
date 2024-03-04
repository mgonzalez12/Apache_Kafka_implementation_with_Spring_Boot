package com.springcloud.producer.domian;

import com.springcloud.producer.enums.StockEventType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockEvent {

    private Integer stockEventId;
    private StockEventType stockEventType;
    @NotNull
    @Valid
    private Stock stock;

}
