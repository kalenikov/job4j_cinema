package ru.job4j.cinema.repository.jdbc;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.TicketRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcTicketRepository implements TicketRepository {

    private static final class SingletonHolder {
        private static final JdbcTicketRepository INSTANCE = new JdbcTicketRepository();
    }

    public static JdbcTicketRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Ticket save(Ticket ticket) throws SQLException {
        Connection cn = ConnectionPool.getConnection();
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO ticket (session_id, row, cell, account_id) VALUES (?, ?, ?, ?)";
        int key = runner.update(cn, sql,
                ticket.getSessionId(),
                ticket.getRow(),
                ticket.getCell(),
                ticket.getAccountId());
        ticket.setId(key);
        return ticket;
    }

    @Override
    public List<Ticket> getBySessionId(Integer sessionId) throws SQLException {
        Connection cn = ConnectionPool.getConnection();
        QueryRunner runner = new QueryRunner();
        BeanListHandler<Ticket> beanListHandler = new TicketListHandler();
        return runner.query(cn, "SELECT * FROM ticket WHERE session_id=?", beanListHandler, sessionId);
    }
}
