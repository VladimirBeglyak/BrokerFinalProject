package by.broker.http.service;

import by.broker.http.dao.StockDao;
import by.broker.http.dto.StockDto;
import by.broker.http.entity.Stock;
import by.broker.http.mapper.CreateStockMapper;
import by.broker.http.mapper.ListStockMapper;
import by.broker.http.mapper.StockByIdMapper;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class StockService {
    private static StockService INSTANCE=null;

    private StockService(){}

    public static StockService getInstance() {
        if (INSTANCE==null){
            synchronized (StockService.class){
                if (INSTANCE==null){
                    INSTANCE=new StockService();
                }
            }
        }
        return INSTANCE;
    }

    private final StockDao stockDao=StockDao.getInstance();
    private final CreateStockMapper createStockMapper= CreateStockMapper.getInstance();
    private final ListStockMapper listStockMapper=ListStockMapper.getInstance();
    private final StockByIdMapper stockByIdMapper=StockByIdMapper.getInstance();



    public Stock save(StockDto stockDto){
        return stockDao.save(createStockMapper.mapFrom(stockDto));
    }

    public List<StockDto> findAll(){
        return stockDao.findAll().stream()
                .map(stock -> listStockMapper.mapFrom(stock))
                .collect(toList());
    }

    public StockDto findById(Long id){
       return stockDao.findById(id)
               .map(stock -> stockByIdMapper.mapFrom(Optional.of(stock)))
               .get();
    }
}
