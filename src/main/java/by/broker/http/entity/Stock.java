package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Stock {
    Long id;
    String ticker;
    String name;
    BigDecimal cost;
    BigDecimal dividend;
    Currency currency;
}
