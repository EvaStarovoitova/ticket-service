package by.ipprpo.ticket.repository;

import by.ipprpo.ticket.model.Ticket;

public interface TicketRepository {
    void save(Ticket ticket);
    Ticket findById(int id);
}