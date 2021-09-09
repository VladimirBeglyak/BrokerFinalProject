package by.broker.http.dto;

import by.broker.http.entity.Currency;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class StorageStockDto {

    String ticker;
    String name;
    String amount;
    String costOneStock;
    String country;
    String dividend;
    String currency;
}
