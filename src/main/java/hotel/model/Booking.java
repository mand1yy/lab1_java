package hotel.model;

public class Booking {
    private Visitor visitor;
    private Room room;
    private int nights;

    public Booking(Visitor visitor, Room room, int nights) {
        this.visitor = visitor;
        this.room = room;
        this.nights = nights;
    }

    public double calculateTotal() {
        return room.getPricePerNight() * nights;
    }

    public Visitor getVisitor() { return visitor; }
    public Room getRoom() { return room; }

    @Override
    public String toString() {
        return "Booking{" +
                "visitor=" + visitor +
                ", room=" + room +
                ", nights=" + nights +
                ", total=" + calculateTotal() +
                '}';
    }
}
