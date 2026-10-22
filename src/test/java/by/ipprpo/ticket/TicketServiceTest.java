package by.ipprpo.ticket;

import by.ipprpo.ticket.model.Customer;
import by.ipprpo.ticket.model.Event;
import by.ipprpo.ticket.model.Ticket;
import by.ipprpo.ticket.repository.InMemoryTicketRepository;
import by.ipprpo.ticket.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {

    private TicketService service;
    private Event event;
    private Customer customer;

    @BeforeEach
    void setUp() {
        service = new TicketService(new InMemoryTicketRepository());
        event = new Event(1, "Кино", 1);
        customer = new Customer(1, "Алексей");
    }

    // Тест 1: Успешная покупка
    @Test
    void testBookTicketSuccess() {
        Ticket ticket = service.bookTicket(event, customer);
        assertNotNull(ticket);
        assertEquals(0, event.availableSeats);
    }

    // Тест 2: Ошибка, если места закончились
    @Test
    void testBookTicketNoSeats() {
        service.bookTicket(event, customer); // забрали единственное место
        Customer customer2 = new Customer(2, "Ольга");

        assertThrows(RuntimeException.class, () -> {
            service.bookTicket(event, customer2);
        });
    }

    // Тест 3: Отмена билета возвращает свободное место
    @Test
    void testCancelTicket() {
        Ticket ticket = service.bookTicket(event, customer);
        assertEquals(0, event.availableSeats);

        service.cancelTicket(ticket.id);
        assertEquals(1, event.availableSeats); // место вернулось
        assertTrue(ticket.isCancelled);
    }


    @Test
    void testTicketCreationAndAttributes() {
        Ticket ticket = service.bookTicket(event, customer);

        assertNotNull(ticket, "Билет должен быть создан");
        assertEquals(event, ticket.event, "Событие в билете должно совпадать с заданным");
        assertEquals(customer, ticket.customer, "Покупатель билета должен совпадать с заданным");
        assertFalse(ticket.isCancelled, "Новый созданный билет не должен быть отменен");
    }
}