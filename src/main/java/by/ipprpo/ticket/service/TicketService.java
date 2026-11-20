package by.ipprpo.ticket.service;
import java.util.*;
import by.ipprpo.ticket.model.Customer;
import by.ipprpo.ticket.model.Event;
import by.ipprpo.ticket.model.Ticket;
import by.ipprpo.ticket.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketService.class);


    private TicketRepository repository;
    private int ticketCounter = 1;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }


    public Ticket bookTicket(Event event, Customer customer) {
        if (event == null || customer == null) {
            log.error("Ошибка покупки билета: событие или покупатель не заданы (null)");
            throw new IllegalArgumentException("Событие и покупатель не могут быть null!");
        }

        log.info("Запрос на покупку билета: eventId={}, customerId={}", event.id, customer.id);

        if (event.availableSeats <= 0) {
            log.warn("Попытка покупки билета при отсутствии доступных мест: eventId={}", event.id);
            throw new RuntimeException("Нет мест!");
        }

        event.availableSeats--;
        Ticket ticket = new Ticket(ticketCounter++, event, customer);
        repository.save(ticket);

        log.info("Билет успешно оформлен: ticketId={}, осталось мест: {}", ticket.id, event.availableSeats);
        return ticket;
    }


    public void cancelTicket(int ticketId) {
        Ticket ticket = repository.findById(ticketId);
        if (ticket == null || ticket.isCancelled) {
            log.error("Ошибка отмены билета: ticketId={} не найден или уже отменен", ticketId);
            throw new RuntimeException("Билет не найден или уже отменен!");
        }
        ticket.isCancelled = true;
        ticket.event.availableSeats++;
        log.info("Билет ticketId={} отменен. Свободных мест: {}", ticketId, ticket.event.availableSeats);
    }
}