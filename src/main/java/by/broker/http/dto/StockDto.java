package by.broker.http.dto;

import by.broker.http.entity.Currency;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class StockDto{

    Long id;
    String ticker;
    String name;
    String cost;
    String dividend;
    String currency;


}
