package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
public class StorageStock {

    private Long id;
    private String ticker;
    private String name;
    private Long amount;
    private BigDecimal costOneStock;
    private String county;
    private BigDecimal dividend;
    private Currency currency;

}
