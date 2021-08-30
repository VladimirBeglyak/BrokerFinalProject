package by.broker.http.mapper;

import by.broker.http.dto.StockDto;
import by.broker.http.entity.Currency;
import by.broker.http.entity.Stock;

import java.math.BigDecimal;

public class CreateUserMapper implements Mapper<StockDto, Stock>{

    private static CreateUserMapper INSTANCE=null;

    private CreateUserMapper(){}

    public static CreateUserMapper getInstance() {
        if (INSTANCE==null){
            synchronized (CreateUserMapper.class){
                if (INSTANCE==null){
                    INSTANCE=new CreateUserMapper();
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
