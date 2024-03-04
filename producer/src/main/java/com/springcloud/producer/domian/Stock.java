package com.springcloud.producer.domian;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Stock {

    @NotNull
    private Integer stockId;
    @NotBlank
    private String stockName;
    @NotBlank
    private String stockDescription;
}
