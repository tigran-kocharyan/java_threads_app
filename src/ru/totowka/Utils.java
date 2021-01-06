package ru.totowka;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Вспомогательные функции для данной задачи.
 *
 * @author <a href="mailto:tskocharyan@edu.hse.ru"> Tigran Kocharyan</a>
 */
public final class Utils {
    /**
     * Конструктор без параметров.
     */
    private Utils() {
    }

    /**
     * Перевод лямбды в TimerTask.
     *
     * @param runnable лямбда-функция.
     * @return созданный из лямбды TimerTask.
     */
    public static TimerTask getTask(Runnable runnable) {
        return new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
    }

    /**
     * @param args Аргументы консоли.
     * @return Если параметров 0, то создаем точку с координатами по-умолчанию.
     * Если параметров 1, то создаем точку с координатами x = args[0], а y по-умолчанию.
     * Если параметров 2, создаем точку с координатами x = args[0] и y = args[1].
     * @throws NullPointerException  если указатель на строку Null.
     * @throws NumberFormatException если формат числа некорректен.
     * @throws ParseException        при ошибке парсинга.
     */
    public static Cart getCart(String[] args) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        if (args.length == 2) {
            double x = format.parse(args[0]).doubleValue();
            double y = format.parse(args[1]).doubleValue();
            return new Cart(x, y);
        } else if (args.length == 1) {
            double x = format.parse(args[0]).doubleValue();
            return new Cart(x, 0);
        } else {
            return new Cart(0, 0);
        }
    }

    /**
     * Генератор рандомных чисел.
     *
     * @param min минимум.
     * @param max максимум.
     * @return рандомное число в диапазоне от min до max.
     */
    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}