package ru.job4j.cinema.repository.jdbc;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.job4j.cinema.model.Account;
import ru.job4j.cinema.repository.AccountRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcAccountRepository implements AccountRepository {

    private static final class SingletonHolder {
        private static final JdbcAccountRepository INSTANCE = new JdbcAccountRepository();
    }

    public static JdbcAccountRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Account save(Account account) throws SQLException {
        Connection cn = ConnectionPool.getConnection();
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO account (username, phone) VALUES (?, ?)";
        int key = runner.update(cn, sql,
                account.getUsername(),
                account.getPhone());
        account.setId(key);
        return account;
    }

    @Override
    public Account getByPhone(String phone) throws SQLException {
        Connection cn = ConnectionPool.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanHandler<Account> beanHandler = new BeanHandler<>(Account.class);
        return runner.query(cn, "SELECT * FROM account where phone=?", beanHandler, phone);
    }
}
