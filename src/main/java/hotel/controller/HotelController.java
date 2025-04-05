package hotel.controller;

import hotel.model.*;
import hotel.viev.ConsoleView;
import java.io.IOException;

public class HotelController {
    private final Hotel hotel = new Hotel();
    private final ConsoleView view = new ConsoleView();

    public void start() {
        while (true) {
            int choice = view.showMainMenu();
            switch (choice) {
                case 1 -> addRoom();
                case 2 -> addVisitor();
                case 3 -> makeBooking();
                case 4 -> hotel.showStatistics();
                case 5 -> crudMenu();
                case 6 -> importExportMenu();
                case 0 -> System.exit(0);
            }
        }
    }

    private void addRoom() {
        var scanner = view.getScanner();
        view.printMessage("Номер кімнати: ");
        int number = scanner.nextInt();
        view.printMessage("Ціна за ніч: ");
        double price = scanner.nextDouble();
        hotel.addRoom(new Room(number, price));
    }

    private void addVisitor() {
        var scanner = view.getScanner();
        scanner.nextLine();
        view.printMessage("Ім’я: ");
        String name = scanner.nextLine();
        view.printMessage("Паспорт: ");
        String passport = scanner.nextLine();
        hotel.addVisitor(new Visitor(name, passport));
    }

    private void makeBooking() {
        var scanner = view.getScanner();
        scanner.nextLine();
        view.printMessage("Паспорт відвідувача: ");
        String passport = scanner.nextLine();
        view.printMessage("Номер кімнати: ");
        int number = scanner.nextInt();
        view.printMessage("Кількість ночей: ");
        int nights = scanner.nextInt();
        Visitor v = hotel.findVisitor(passport);
        Room r = hotel.findRoom(number);
        if (v != null && r != null && r.isAvailable()) {
            hotel.addBooking(new Booking(v, r, nights));
            view.printMessage("Бронювання успішне!");
        } else {
            view.printMessage("Бронювання не вдалося");
        }
    }

    private void crudMenu() {
        var scanner = view.getScanner();
        view.printMessage("1. Переглянути кімнати\n2. Видалити кімнату\n3. Переглянути відвідувачів\n4. Видалити відвідувача");
        int op = scanner.nextInt();
        scanner.nextLine();
        switch (op) {
            case 1 -> hotel.getRooms().forEach(System.out::println);
            case 2 -> {
                view.printMessage("Номер кімнати: ");
                int n = scanner.nextInt();
                hotel.deleteRoom(n);
            }
            case 3 -> hotel.getVisitors().forEach(System.out::println);
            case 4 -> {
                view.printMessage("Паспорт: ");
                String p = scanner.nextLine();
                hotel.deleteVisitor(p);
            }
        }
    }

    private void importExportMenu() {
        var scanner = view.getScanner();
        view.printMessage("1. Експорт\n2. Імпорт\n3. Сортувати кімнати за ціною");
        int ex = scanner.nextInt();
        scanner.nextLine();
        switch (ex) {
            case 1 -> {
                try {
                    hotel.exportData("rooms.json");
                    view.printMessage("Експортовано!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                try {
                    hotel.importData("rooms.json");
                    view.printMessage("Імпортовано!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            case 3 -> hotel.sortRoomsByPrice();
        }
    }
}
