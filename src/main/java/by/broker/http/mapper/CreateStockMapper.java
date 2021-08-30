package by.broker.http.mapper;

import by.broker.http.dto.StockDto;
import by.broker.http.entity.Currency;
import by.broker.http.entity.Stock;

import java.math.BigDecimal;

public class CreateStockMapper implements Mapper<StockDto, Stock>{

    private static CreateStockMapper INSTANCE=null;

    private CreateStockMapper(){}

    public static CreateStockMapper getInstance() {
        if (INSTANCE==null){
            synchronized (CreateStockMapper.class){
                if (INSTANCE==null){
                    INSTANCE=new CreateStockMapper();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Stock mapFrom(StockDto object) {
        return Stock.builder()
                .name(object.getName())
                .ticker(object.getTicker())
                .cost(BigDecimal.valueOf(Double.parseDouble(object.getCost())))
                .dividend(BigDecimal.valueOf(Double.parseDouble(object.getDividend())))
                .currency(Currency.valueOf(object.getCurrency()))
                .build();
    }
}
