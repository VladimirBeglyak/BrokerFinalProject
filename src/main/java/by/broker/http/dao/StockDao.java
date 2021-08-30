package by.broker.http.dao;

import by.broker.http.dto.StockFilter;
import by.broker.http.entity.Currency;
import by.broker.http.entity.Stock;
import by.broker.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockDao implements Dao<Long,Stock>,FilterDao<Stock,StockFilter>

        /*CreateInterface<Stock>,
        FindInterface<Stock, Integer>,
        DeleteInterface<Integer>,
        UpdateInterface<Stock>,
        FilterDao<Stock, StockFilter>*/ {

    private static final String ADD_STOCK = """
            INSERT INTO stocks 
            (ticker, name, cost, dividend, currency) 
            VALUES (?,?,?,?,?)
            """;
    private static final String FIND_ALL_STOCKS = """
            SELECT 
            id,
            ticker,
            name,
            cost,
            dividend,
            currency 
            FROM stocks
            """;
    private static final String FIND_STOCK_BY_ID = FIND_ALL_STOCKS + """ 
            WHERE id=?
            """;
    private static final String DELETE_STOCK = """
            DELETE FROM stocks
            WHERE id=?
            """;
    private static final String UPDATE_STOCK = """
            UPDATE stocks
            SET ticker=?,
            name=?,
            cost=?,
            dividend=?,
            currency=?
            WHERE id=?
            """;

    private static StockDao INSTANCE = null;

    private StockDao() {
    }

    public static StockDao getInstance() {
        if (INSTANCE == null) {
            synchronized (StockDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StockDao();
                }
            }
        }
        return INSTANCE;
    }

    @SneakyThrows
    @Override
    public Stock save(Stock stock) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_STOCK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, stock.getTicker());
            preparedStatement.setObject(2, stock.getName());
            preparedStatement.setObject(3, stock.getCost());
            preparedStatement.setObject(4, stock.getDividend());
            preparedStatement.setObject(5, stock.getCurrency().name());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                stock.setId(generatedKeys.getObject(1, Long.class));
            }
            return stock;
        }
    }

    @SneakyThrows
    @Override
    public List<Stock> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STOCKS);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stock> stocks = new ArrayList<>();
            while (resultSet.next()) {
                stocks.add(buildStock(resultSet));
            }
            return stocks;
        }
    }

    @SneakyThrows
    @Override
    public Optional<Stock> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return findById(id,connection);
        }
    }

    @SneakyThrows
    public Optional<Stock> findById(Long id, Connection connection){
        try(PreparedStatement preparedStatement=connection.prepareStatement(FIND_STOCK_BY_ID)) {
            preparedStatement.setObject(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            Stock stock = null;
            if (resultSet.next()) {
                stock = buildStock(resultSet);
            }
            return Optional.ofNullable(stock);
        }
    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STOCK);
            preparedStatement.setObject(1, id);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @SneakyThrows
    @Override
    public void update(Stock stock) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STOCK);
            preparedStatement.setObject(1, stock.getTicker());
            preparedStatement.setObject(2, stock.getName());
            preparedStatement.setObject(3, stock.getCost());
            preparedStatement.setObject(4, stock.getDividend());
            preparedStatement.setObject(5, stock.getCurrency().name());
            preparedStatement.setObject(6, stock.getId());

            preparedStatement.executeUpdate();

        }
    }


    private Stock buildStock(ResultSet resultSet) throws java.sql.SQLException {
        return Stock.builder()
                .id(resultSet.getObject("id", Long.class))
                .ticker(resultSet.getObject("ticker", String.class))
                .name(resultSet.getObject("name", String.class))
                .cost(resultSet.getObject("cost", BigDecimal.class))
                .dividend(resultSet.getObject("dividend", BigDecimal.class))
                .currency(Currency.valueOf(resultSet.getObject("currency", String.class)))
                .build();
    }

    @SneakyThrows
    @Override
    public List<Stock> findAll(StockFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql=new ArrayList<>();
        if (filter.getName()!=null){
            whereSql.add("name LIKE ?");
            parameters.add("%"+filter.getName()+"%");
        }
        if (filter.getCurrency()!=null){
            whereSql.add("currency=?");
            parameters.add(filter.getCurrency());
        }
        parameters.add(filter.getLimit());
        parameters.add(filter.getOffset());
        String where = whereSql.stream()
                .collect(Collectors.joining(" AND "," WHERE "," LIMIT ? OFFSET ? "));

        String sql = FIND_ALL_STOCKS +where;
                /*+ """
                LIMIT ?
                OFFSET ?
                """;*/
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(parameters);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stock> stocks = new ArrayList<>();
            while (resultSet.next()) {
                stocks.add(buildStock(resultSet));
            }
            return stocks;
        }
    }
}
