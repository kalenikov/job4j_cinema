package ru.job4j.cinema.service;

import lombok.extern.slf4j.Slf4j;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.TicketDTO;
import ru.job4j.cinema.repository.jdbc.JdbcTicketRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class TicketService {

    private static final class SingletonHolder {
        private static final TicketService INSTANCE = new TicketService();
    }

    public static TicketService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<Ticket> getBySessionId(Integer sessionId) {
        JdbcTicketRepository repo = JdbcTicketRepository.getInstance();
        try {
            return repo.getBySessionId(sessionId);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<TicketDTO> getPurchasedPlaces(Integer sessionId) {
        return getBySessionId(sessionId).stream()
                .map(ticket -> new TicketDTO(ticket.getRow(), ticket.getCell()))
                .collect(Collectors.toList());
    }

    public Optional<Ticket> save(Ticket ticket) {
        JdbcTicketRepository repo = JdbcTicketRepository.getInstance();
        try {
            return Optional.ofNullable(repo.save(ticket));
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
}
