package by.ipprpo.ticket.model;

public class Ticket {
    public int id;
    public Event event;
    public Customer customer;
    public boolean isCancelled;

    public Ticket(int id, Event event, Customer customer) {
        this.id = id;
        this.event = event;
        this.customer = customer;
        this.isCancelled = false;
    }
}