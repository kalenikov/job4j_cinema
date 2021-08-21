package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Account;

import java.sql.SQLException;

public interface AccountRepository {
    Account save(Account account) throws SQLException;

    Account getByPhone(String phone) throws SQLException;
}
