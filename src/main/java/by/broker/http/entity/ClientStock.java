package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ClientStock {

    private Long id;
    private String ticker;
    private String name;
    private Long amount;
    private BigDecimal costOneStock;
    private Operation operation;
    private String county;
    private BigDecimal dividend;
    private String currency;

}
