package hotel;

import hotel.model.Booking;
import hotel.model.Hotel;
import hotel.model.Room;
import hotel.model.Visitor;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HotelTest {

    @Test
    public void testAddRoom() {
        Hotel hotel = new Hotel();
        hotel.addRoom(new Room(101, 200.0));
        assertEquals(1, hotel.getRooms().size());
    }

    @Test
    public void testAddDuplicateRoomNumber() {
        Hotel hotel = new Hotel();
        hotel.addRoom(new Room(101, 200.0));
        hotel.addRoom(new Room(101, 250.0));
        assertEquals(2, hotel.getRooms().size(), "Наразі логіка дозволяє дублікати, але можна розширити перевіркою.");
    }

    @Test
    public void testFindVisitorExists() {
        Hotel hotel = new Hotel();
        Visitor visitor = new Visitor("Ivan", "AA123456");
        hotel.addVisitor(visitor);
        assertNotNull(hotel.findVisitor("AA123456"));
    }

    @Test
    public void testFindVisitorNotExists() {
        Hotel hotel = new Hotel();
        assertNull(hotel.findVisitor("XX000000"));
    }

    @Test
    public void testBookingRoomUnavailable() {
        Hotel hotel = new Hotel();
        Room room = new Room(105, 300.0);
        room.setAvailable(false); // вже заброньовано
        hotel.addRoom(room);
        Visitor visitor = new Visitor("Maria", "BB654321");
        hotel.addVisitor(visitor);
        Booking booking = new Booking(visitor, room, 2);
        hotel.addBooking(booking);
        assertFalse(room.isAvailable(), "Кімната вже була недоступна, має залишатись такою.");
    }

    @Test
    public void testDeleteVisitor() {
        Hotel hotel = new Hotel();
        Visitor v = new Visitor("Olga", "CC987654");
        hotel.addVisitor(v);
        assertTrue(hotel.deleteVisitor("CC987654"));
        assertNull(hotel.findVisitor("CC987654"));
    }

    @Test
    public void testSortRoomsByPrice() {
        Hotel hotel = new Hotel();
        hotel.addRoom(new Room(101, 300));
        hotel.addRoom(new Room(102, 100));
        hotel.addRoom(new Room(103, 200));
        hotel.sortRoomsByPrice();
        List<Room> sorted = hotel.getRooms();
        assertEquals(100, sorted.get(0).getPricePerNight());
        assertEquals(300, sorted.get(2).getPricePerNight());
    }

    @Test
    public void testExportImportRooms() throws IOException {
        Hotel hotel = new Hotel();
        hotel.addRoom(new Room(201, 150));
        hotel.exportData("test_rooms.json");

        Hotel newHotel = new Hotel();
        newHotel.importData("test_rooms.json");
        assertEquals(1, newHotel.getRooms().size());
        assertEquals(201, newHotel.getRooms().get(0).getRoomNumber());
        new File("test_rooms.json").delete();
    }

    @Test
    public void testCalculateBookingTotal() {
        Room room = new Room(301, 100);
        Visitor visitor = new Visitor("Petro", "EE456789");
        Booking booking = new Booking(visitor, room, 3);
        assertEquals(300.0, booking.calculateTotal());
    }
}
