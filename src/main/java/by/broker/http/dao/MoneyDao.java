package by.broker.http.dao;

import by.broker.http.entity.Money;
import by.broker.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoneyDao implements Dao<Long, Money> {


    private static MoneyDao INSTANCE;
    private MoneyDao() {
    }

    public static MoneyDao getInstance() {
        if (INSTANCE == null) {
            synchronized (MoneyDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MoneyDao();
                }
            }
        }
        return INSTANCE;
    }


    private static final String FIND_ALL_MONEY_BY_CLIENT= """
            SELECT * FROM money
            WHERE client_id=?     
            """;

    private static final String DELETE_SQL = """
            DELETE FROM money
            WHERE id=?
            """;
    private static final String SAVE_MONEY= """
            INSERT INTO money 
            (amount, currency_id,client_id) VALUES (?,?,?)
            """;
    private static final String UPDATE_MONEY= """
            UPDATE money
           SET amount=?,
           currency_id=?
           WHERE client_id=?
            """;
    private static final String FIND_BY_ID= """
            SELECT * FROM money
            WHERE id=?
            """;
    private static final String FIND_ALL= """
            SELECT * FROM money
            """;

    private final CurrencyDao currencyDao=CurrencyDao.getInstance();


    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setObject(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @SneakyThrows
    @Override
    public Money save(Money money) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_MONEY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,money.getAmount());
            preparedStatement.setObject(2,money.getCurrency().getId());
            preparedStatement.setObject(3,money.getClient().getId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                money.setId(generatedKeys.getObject("id",Long.class));
            }
            return money;
        }
    }

    @SneakyThrows
    @Override
    public void update(Money money) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MONEY);
            preparedStatement.setObject(1,BigDecimal.class);
            preparedStatement.setObject(2,money.getCurrency().getId());
            preparedStatement.setObject(3,money.getClient().getId());
            preparedStatement.executeUpdate();
        }

    }

    @SneakyThrows
    @Override
    public Optional<Money> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Money money=null;
            while (resultSet.next()){
                money=buildMoney(resultSet);
            }
            return Optional.ofNullable(money);
        }
    }

    private Money buildMoney(ResultSet resultSet) throws SQLException {
        return Money.builder()
                .id(resultSet.getObject("id",Long.class))
                .amount(resultSet.getObject("amount",BigDecimal.class))
                .currency(currencyDao.findById(resultSet.getObject("currency_id",Long.class)).orElse(null))
                .build();
    }

    @SneakyThrows
    @Override
    public List<Money> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Money> monies=new ArrayList<>();
            while (resultSet.next()){
                monies.add(buildMoney(resultSet));
            }
            return monies;
        }
    }

    @SneakyThrows
    public List<Money> findAllByClientId(Long id){
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_MONEY_BY_CLIENT);
        preparedStatement.setObject(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Money> monies=new ArrayList<>();
        while (resultSet.next()){
            monies.add(buildMoney(resultSet));
        }
        return monies;
    }
}
