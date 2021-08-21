package ru.job4j.cinema.service;

import lombok.extern.slf4j.Slf4j;
import ru.job4j.cinema.model.Account;
import ru.job4j.cinema.repository.jdbc.JdbcAccountRepository;

import java.sql.SQLException;
import java.util.Optional;

@Slf4j
public class AccountService {

    private static final class SingletonHolder {
        private static final AccountService INSTANCE = new AccountService();
    }

    public static AccountService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Optional<Account> getByPhone(String phone) {
        JdbcAccountRepository repo = JdbcAccountRepository.getInstance();
        try {
            return Optional.ofNullable(repo.getByPhone(phone));
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<Account> save(Account account) {
        JdbcAccountRepository repo = JdbcAccountRepository.getInstance();
        try {
            return Optional.ofNullable(repo.save(account));
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
}
