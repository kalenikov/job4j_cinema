package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketRepository {
    Ticket save(Ticket ticket) throws SQLException;

    List<Ticket> getBySessionId(Integer sessionId) throws SQLException;
}
