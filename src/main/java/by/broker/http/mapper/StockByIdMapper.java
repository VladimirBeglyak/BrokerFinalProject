package by.broker.http.mapper;

import by.broker.http.dto.StockDto;
import by.broker.http.entity.Stock;

import java.util.Optional;

public class StockByIdMapper implements Mapper<Optional<Stock>, StockDto> {

    private static StockByIdMapper INSTANCE = null;

    private StockByIdMapper() {
    }

    public static StockByIdMapper getInstance() {
        if (INSTANCE == null) {
            synchronized (StockByIdMapper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StockByIdMapper();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public StockDto mapFrom(Optional<Stock> object) {
        return object.map(stock ->
                StockDto.builder()
                        .id(stock.getId())
                        .name(stock.getName())
                        .ticker(stock.getTicker())
                        .cost(stock.getCost().toString())
                        .dividend(stock.getDividend().toString())
                        .currency(stock.getCurrency().name())
                        .build()).get();
    }
}
