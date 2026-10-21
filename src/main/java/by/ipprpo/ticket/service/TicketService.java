package by.ipprpo.ticket.service;

import by.ipprpo.ticket.model.Customer;
import by.ipprpo.ticket.model.Event;
import by.ipprpo.ticket.model.Ticket;
import by.ipprpo.ticket.repository.TicketRepository;

public class TicketService {
    // Внедрение зависимости через интерфейс (DI)
    private TicketRepository repository;
    private int ticketCounter = 1;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    // Покупка билета
    public Ticket bookTicket(Event event, Customer customer) {
        if (event.availableSeats <= 0) {
            throw new RuntimeException("Нет мест!");
        }
        event.availableSeats--;
        Ticket ticket = new Ticket(ticketCounter++, event, customer);
        repository.save(ticket);
        return ticket;
    }

    // Индивидуальное задание: отмена билета с возвратом места
    public void cancelTicket(int ticketId) {
        Ticket ticket = repository.findById(ticketId);
        if (ticket == null || ticket.isCancelled) {
            throw new RuntimeException("Билет не найден или уже отменен!");
        }
        ticket.isCancelled = true;
        ticket.event.availableSeats++;
    }
}