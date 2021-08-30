package by.broker.http.util;

import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL_KEY="db.url";
    private static final String USER_KEY="db.user";
    private static final String PASSWORD_KEY="db.password";
    private static final String DRIVER_KEY="db.driver";
    private static DataSource dataSource;

    static {
        /*PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(PropertiesUtil.get(URL_KEY));
        poolProperties.setUsername(PropertiesUtil.get(USER_KEY));
        poolProperties.setPassword(PropertiesUtil.get(PASSWORD_KEY));
        poolProperties.setDriverClassName(PropertiesUtil.get(DRIVER_KEY));
        dataSource=new DataSource(poolProperties);*/
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PropertiesUtil.get(URL_KEY),
                PropertiesUtil.get(USER_KEY),
                PropertiesUtil.get(PASSWORD_KEY)
        );
    }
}
