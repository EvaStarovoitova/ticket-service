package by.ipprpo.ticket.model;

public class Event {
    public int id;
    public String title;
    public int availableSeats;

    public Event(int id, String title, int seats) {
        this.id = id;
        this.title = title;
        this.availableSeats = seats;
    }
}