package ru.job4j.cinema.model;

import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@ToString
@Setter
@Getter
public class Ticket {
    private int id;
    private int sessionId;
    private int row;
    private int cell;
    private int accountId;

    public Ticket(int sessionId, int row, int cell, int accountId) {
        this.sessionId = sessionId;
        this.row = row;
        this.cell = cell;
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
