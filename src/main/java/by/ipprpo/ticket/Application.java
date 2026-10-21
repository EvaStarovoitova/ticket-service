package by.ipprpo.ticket;

import by.ipprpo.ticket.model.Customer;
import by.ipprpo.ticket.model.Event;
import by.ipprpo.ticket.model.Ticket;
import by.ipprpo.ticket.repository.InMemoryTicketRepository;
import by.ipprpo.ticket.repository.TicketRepository;
import by.ipprpo.ticket.service.TicketService;

public class Application {
    public static void main(String[] args) {
        TicketRepository repository = new InMemoryTicketRepository();
        TicketService service = new TicketService(repository);//внедряем в сервис через контсруктор

        Event event = new Event(1, "Концерт", 1);
        Customer customer = new Customer(1, "Иван");

        System.out.println("Мест в начале: " + event.availableSeats);

        // Покупка
        Ticket ticket = service.bookTicket(event, customer);
        System.out.println("Билет куплен, мест осталось: " + event.availableSeats);

        // Отмена
        service.cancelTicket(ticket.id);
        System.out.println("Билет отменен, мест стало: " + event.availableSeats);
    }
}