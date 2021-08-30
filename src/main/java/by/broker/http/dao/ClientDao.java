package by.broker.http.dao;

import by.broker.http.dto.ClientFilter;
import by.broker.http.entity.*;
import by.broker.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientDao implements Dao<Long, Client>,
        FilterDao<Client, ClientFilter> {

    private static final String SAVE_CLINET = """
            INSERT INTO clients (first_name, last_name, father_name, birthday, passport_id, password, email, role,stock_id) 
            VALUES (?,?,?,?,?,?,?,?,?)
            """;

    private static final String UPDATE_CLIENT = """
            UPDATE clients
            SET first_name=?,
            last_name=?,
            father_name=?,
            birthday=?,
            passport_id=?,
            password=?,
            email=?,
            role=?,
            stock_id=?
            WHERE id=?
            """;


    private static final String FIND_ALL_CLIENTS = """
            SELECT 
            clients.id,
                   first_name,
                   last_name,
                   father_name,
                   birthday,
                   passport_id,
                   password,
                   email,
                   role,
                   stock_id,
                   s.id,
                   s.ticker,
                   s.name,
                   s.currency,
                   s.cost,
                   s.dividend
            FROM clients
            JOIN stocks s 
            ON clients.stock_id=s.id
            """;

    private static final String FIND_BY_ID_CLIENT = FIND_ALL_CLIENTS + """
            WHERE clients.id=?
            """;

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ClientDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientDao();
                }
            }
        }
        return INSTANCE;
    }

    private final StockDao stockDao = StockDao.getInstance();

    @SneakyThrows
    @Override
    public Client save(Client client) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CLINET, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, client.getFirstName());
            preparedStatement.setObject(2, client.getLastName());
            preparedStatement.setObject(3, client.getFatherName());
            preparedStatement.setObject(4, client.getBirthday());
            preparedStatement.setObject(5, client.getPassportId());
            preparedStatement.setObject(6, client.getPassword());
            preparedStatement.setObject(7, client.getEmail());
            preparedStatement.setObject(8, client.getRole().name());
            preparedStatement.setObject(9,client.getStock().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getObject("id", Long.class));
            }
            return client;
        }
    }


    @SneakyThrows
    @Override
    public List<Client> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CLIENTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        }
    }

    @SneakyThrows
    @Override
    public Optional<Client> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_CLIENT);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //System.out.println(preparedStatement);
            Client client = null;
            if (resultSet.next()) {
                client = buildClient(resultSet);
            }
            return Optional.ofNullable(client);
        }
    }

    @SneakyThrows
    @Override
    public void update(Client client) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
            preparedStatement.setObject(1, client.getFirstName());
            preparedStatement.setObject(2, client.getLastName());
            preparedStatement.setObject(3, client.getFatherName());
            preparedStatement.setObject(4, client.getBirthday());
            preparedStatement.setObject(5, client.getPassportId());
            preparedStatement.setObject(6, client.getPassword());
            preparedStatement.setObject(7, client.getEmail());
            preparedStatement.setObject(8, client.getRole().name());
            preparedStatement.setObject(9,client.getStock().getId());
            preparedStatement.setObject(10, client.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @SneakyThrows
    @Override
    public List<Client> findAll(ClientFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.getFirstName() != null) {
            whereSql.add("first_name LIKE ?");
            parameters.add("%" + filter.getFirstName() + "%");
        }
        if (filter.getLastName() != null) {
            whereSql.add("last_name LIKE ?");
            parameters.add("%" + filter.getLastName() + "%");
        }
        if (filter.getEmail() != null) {
            whereSql.add("email LIKE ?");
            parameters.add("%" + filter.getEmail() + "%");
        }
        parameters.add(filter.getLimit());
        parameters.add(filter.getOffset());
        String where = whereSql.stream()
                .collect(Collectors.joining(" AND ", " WHERE ", " LIMIT ? OFFSET ? "));

        String sql = FIND_ALL_CLIENTS + where;
                /*+ """
                LIMIT ?
                OFFSET ?
                """;*/
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(whereSql);
            System.out.println(parameters);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        }
    }

    private Client buildClient(ResultSet resultSet) throws SQLException {

        return Client.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getObject("first_name", String.class))
                .lastName(resultSet.getObject("last_name", String.class))
                .fatherName(resultSet.getObject("father_name", String.class))
                .birthday(resultSet.getObject("birthday", LocalDate.class))
                .passportId(resultSet.getObject("passport_id", String.class))
                .password(resultSet.getObject("password", String.class))
                .email(resultSet.getObject("email", String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .stock(stockDao.findById(resultSet.getObject("stock_id", Long.class),
                        resultSet.getStatement().getConnection()).orElse(null))
                .build();
    }

    private static ClientDao INSTANCE = null;


}
