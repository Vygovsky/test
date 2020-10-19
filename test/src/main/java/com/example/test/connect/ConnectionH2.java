/*
package com.example.test.connect;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class ConnectionH2 {

    private static final String URL = "jdbc:h2:tcp://localhost/~/testTest";
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String PASSWORD = "";
    private static final String USER = "sa";


    private Connection connection = null;

    public Connection createConnection() throws SQLException {
        try {
            connection = new HikariDataSource(getHikariConfig()).getConnection();
        } catch (SQLException e) {
            System.out.println("Error");
        }
        return connection;
    }

    private HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(null);
        config.setDriverClassName(JDBC_DRIVER);
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(25);
        config.setAllowPoolSuspension(true);
        return config;
    }
}
*/
