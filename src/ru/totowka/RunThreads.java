package ru.totowka;

import java.text.ParseException;
import java.util.Timer;

/**
 * Запуск потоков и выполнение основных фукнций.
 *
 * @author <a href="mailto:tskocharyan@edu.hse.ru"> Tigran Kocharyan</a>
 */
public class RunThreads {
    /**
     * Вынесенные константы.
     */
    public static final int END_MILLIS = 25000;
    public static final int INTERVAL_MILLIS = 2000;
    public static final int SWAN_ANGLE = 60;
    public static final int CRAYFISH_ANGLE = 180;
    public static final int PIKE_ANGLE = 300;

    /**
     * Последовательный вызов аргументов.
     *
     * @param args аргументы из консоли
     */
    public static void main(String[] args) {
        // Создание трех животных и тележки.
        Animal swan = new Animal(SWAN_ANGLE, "Swan");
        Animal crayfish = new Animal(CRAYFISH_ANGLE, "Crayfish");
        Animal pike = new Animal(PIKE_ANGLE, "Pike");
        Cart cart;

        // Считывание данных из консоли и проверка в getCart().
        // Если возникают исключения, отлавливаем их и завершаем работу.
        try {
            cart = Utils.getCart(args);
            System.out.println(cart.toString());
        } catch (ParseException | NullPointerException | NumberFormatException ex) {
            System.out.println("The input data is wrong!");
            return;
        }

        // Создание таймера и его задачи для вывода раз в 2 секунды позицию тележки.
        Timer positionOutput = new Timer(true);
        positionOutput.schedule(Utils.getTask(() -> System.out.println(cart.toString())),
                INTERVAL_MILLIS, INTERVAL_MILLIS);

        // Создание потоков и их запуск.
        Thread swanThread = new Thread(() -> cart.move(swan));
        Thread crayfishThread = new Thread(() -> cart.move(crayfish));
        Thread pikeThread = new Thread(() -> cart.move(pike));
        swanThread.start();
        crayfishThread.start();
        pikeThread.start();

        // Создание таймера и его задачи на завершение потоков через 25 секунд.
        Timer stopThreads = new Timer(true);
        stopThreads.schedule(Utils.getTask(() ->
        {
            System.out.println("\nTIME IS OUT!");
            System.out.println("[Cart] final position is at: " + cart.getCoordinates().toString());
            swanThread.interrupt();
            crayfishThread.interrupt();
            pikeThread.interrupt();
        }), END_MILLIS);

        // Дабы потоки не завершились раньше времени, делаем .join().
        try {
            swanThread.join();
            crayfishThread.join();
            pikeThread.join();
        } catch (InterruptedException exception) {
            return;
        }
    }
}
