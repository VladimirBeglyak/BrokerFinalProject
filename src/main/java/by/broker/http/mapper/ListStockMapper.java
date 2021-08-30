package by.broker.http.mapper;

import by.broker.http.dto.StockDto;
import by.broker.http.entity.Stock;

public class ListStockMapper implements Mapper<Stock, StockDto> {

    private static ListStockMapper INSTANCE=null;

    private ListStockMapper(){}

    public static ListStockMapper getInstance() {
        if (INSTANCE==null){
            synchronized (ListStockMapper.class){
                if (INSTANCE==null){
                    INSTANCE=new ListStockMapper();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public StockDto mapFrom(Stock object) {
        return StockDto.builder()
                .id(object.getId())
                .name(object.getName())
                .ticker(object.getTicker())
                .cost(object.getCost().toString())
                .dividend(object.getDividend().toString())
                .currency(object.getCurrency().name())
                .build();
    }
}
