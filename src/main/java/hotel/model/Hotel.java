package hotel.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Hotel {
    private List<Room> rooms = new ArrayList<>();
    private List<Visitor> visitors = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public void addRoom(Room room) { rooms.add(room); }
    public void addVisitor(Visitor visitor) { visitors.add(visitor); }
    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.getRoom().setAvailable(false);
    }

    public List<Room> getRooms() { return rooms; }
    public List<Visitor> getVisitors() { return visitors; }
    public List<Booking> getBookings() { return bookings; }

    public void showStatistics() {
        long availableCount = rooms.stream().filter(Room::isAvailable).count();
        long bookedCount = rooms.size() - availableCount;
        System.out.println("Available rooms: " + availableCount);
        System.out.println("Booked rooms: " + bookedCount);
    }

    public Room findRoom(int roomNumber) {
        return rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().orElse(null);
    }

    public Visitor findVisitor(String passportId) {
        return visitors.stream().filter(v -> v.getPassportId().equals(passportId)).findFirst().orElse(null);
    }

    public boolean deleteRoom(int roomNumber) {
        return rooms.removeIf(r -> r.getRoomNumber() == roomNumber);
    }

    public boolean deleteVisitor(String passportId) {
        return visitors.removeIf(v -> v.getPassportId().equals(passportId));
    }

    public void exportData(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), rooms);
    }

    public void importData(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Room> imported = mapper.readValue(new File(filename), new TypeReference<List<Room>>() {});
        rooms.clear();
        rooms.addAll(imported);
    }

    public void sortRoomsByPrice() {
        rooms = rooms.stream().sorted(Comparator.comparingDouble(Room::getPricePerNight)).collect(Collectors.toList());
    }
}
