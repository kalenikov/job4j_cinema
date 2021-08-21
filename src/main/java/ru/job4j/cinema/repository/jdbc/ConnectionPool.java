package ru.job4j.cinema.repository.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class ConnectionPool {
    private final BasicDataSource pool = new BasicDataSource();

    private static final class SingletonHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static Connection getConnection() throws SQLException {
        return SingletonHolder.INSTANCE.pool.getConnection();
    }

    private ConnectionPool() {
        Properties cfg = new Properties();
        try (var io = ConnectionPool.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }
}
