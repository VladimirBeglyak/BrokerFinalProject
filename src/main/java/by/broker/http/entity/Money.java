package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class Money {

    private Long id;
    private BigDecimal amount;
    private Currency currency;
    private Client client;

}
