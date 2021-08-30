package by.broker.http.dao;

import by.broker.http.entity.Currenci;
import by.broker.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrenciDao implements Dao<Long,Currenci>{

    private static CurrenciDao INSTANCE=null;
    private CurrenciDao(){}
    private static final String UPDATE_SQL= """
            UPDATE currencies
            SET ticker = ?,
            name=?
            WHERE id=?
            """;

    private static final String ADD_CURRENCY= """
            INSERT INTO currencies (ticker, name) VALUES (?,?)
            """;
    private static final String FIND_ALL_SQL= """
            SELECT 
            id,
            ticker,
            name
            FROM currencies
            """;

    private static final String FIND_BY_ID= FIND_ALL_SQL+ """
            WHERE id=?
            """;
    private static final String DELETE_SQL= """
            DELETE FROM currencies WHERE id=?;
            """;

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setObject(1,id);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @SneakyThrows
    @Override
    public Currenci save(Currenci currenci) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CURRENCY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,currenci.getTicker());
            preparedStatement.setObject(2,currenci.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                currenci.setId(generatedKeys.getObject(1,Long.class));
            }
            return currenci;
        }
    }

    @SneakyThrows
    @Override
    public void update(Currenci currenci) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setObject(1,currenci.getTicker());
            preparedStatement.setObject(2,currenci.getName());
            preparedStatement.setObject(3,currenci.getId());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public Optional<Currenci> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Currenci currenci=null;

            if (resultSet.next()){
                currenci= buildCurrenci(resultSet);
            }
            return Optional.ofNullable(currenci);
        }
    }

    @SneakyThrows
    @Override
    public List<Currenci> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Currenci> currencis=new ArrayList<>();
            while (resultSet.next()){
                currencis.add(buildCurrenci(resultSet));
            }
            return currencis;
        }
    }

    private Currenci buildCurrenci(ResultSet resultSet) throws SQLException {
        return Currenci.builder()
                .id(resultSet.getObject("id",Long.class))
                .ticker(resultSet.getObject("ticker",String.class))
                .name(resultSet.getObject("name",String.class))
                .build();
    }

    public static CurrenciDao getInstance() {
        if (INSTANCE==null){
            synchronized (CurrenciDao.class){
                if (INSTANCE==null){
                    INSTANCE=new CurrenciDao();
                }
            }
        }
        return INSTANCE;
    }
}
