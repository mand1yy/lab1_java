package hotel.viev;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public int showMainMenu() {
        System.out.println("\n--- Головне меню ---");
        System.out.println("1. Додати номер");
        System.out.println("2. Додати відвідувача");
        System.out.println("3. Забронювати номер");
        System.out.println("4. Статистика");
        System.out.println("5. CRUD кімнат / відвідувачів");
        System.out.println("6. Імпорт/експорт номерів");
        System.out.println("0. Вихід");
        return scanner.nextInt();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void printMessage(String msg) {
        System.out.println(msg);
    }
}
