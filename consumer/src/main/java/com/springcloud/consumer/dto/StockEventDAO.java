package com.springcloud.consumer.dto;

import com.springcloud.consumer.enums.StockEventTypeDAO;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class StockEventDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockEventId;

    @Enumerated(EnumType.STRING)
    private StockEventTypeDAO stockEventType;
    @OneToOne(mappedBy = "stockEvent", cascade = CascadeType.ALL)
    @ToString.Exclude
    private StockDAO stock;
}
