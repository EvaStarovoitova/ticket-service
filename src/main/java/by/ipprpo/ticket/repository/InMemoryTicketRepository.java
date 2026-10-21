package by.ipprpo.ticket.repository;

import by.ipprpo.ticket.model.Ticket;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTicketRepository implements TicketRepository {
    private Map<Integer, Ticket> storage = new HashMap<>();

    @Override
    public void save(Ticket ticket) {
        storage.put(ticket.id, ticket);
    }

    @Override
    public Ticket findById(int id) {
        return storage.get(id);
    }
}