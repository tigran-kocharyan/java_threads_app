package ru.totowka;

/**
 * Класс тележки. Отвечает за ее передвижение.
 *
 * @author <a href="mailto:tskocharyan@edu.hse.ru"> Tigran Kocharyan</a>
 */
public class Cart {
    /**
     * Координаты тележки.
     */
    private final Point coordinates;

    /**
     * Конструктор тележки. Принимает две точки.
     * В зависимости от входных параметров создает точку.
     *
     * @param x координата X.
     * @param y координата Y.
     */
    public Cart(double x, double y) {
        coordinates = new Point(x, y);
    }

    /**
     * @return Координаты тележки.
     */
    public Point getCoordinates() {
        return coordinates;
    }

    /**
     * Передвигает тележку с параметрами животного.
     *
     * @param animal текущее животное.
     */
    public void move(Animal animal) {
        if (animal == null) {
            throw new NullPointerException("Animal is Null!");
        }
        // Пока поток не прерван, выполняет действие.
        while (!Thread.currentThread().isInterrupted()) {
            // Синхронизируемся на координатах, чтобы потоки не конфликтовали.
            synchronized (coordinates) {
                // Считает сдвиг по оси X и оси Y. Затем сдвигает.
                double x = animal.getCoef() * Math.cos(animal.getAngle());
                double y = animal.getCoef() * Math.sin(animal.getAngle());
                coordinates.add(x, y);

                // Считаем время сна и усыпляем поток, если не выпадает исключение.
                int waitTime = Utils.getRandom(1000, 5000);
                System.out.printf("%s moves the Cart and goes sleep for %.2f secs!\n",
                        animal.toString(), (double) waitTime / 1000);
                // Если выпадает исключение, закрываем поток.
                try {
                    coordinates.wait(waitTime);
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    /**
     * @return представление информации о тележке в виде строки.
     */
    @Override
    public String toString() {
        synchronized (coordinates) {
            return "\n[Cart] is now located at: " + coordinates.toString();
        }
    }
}
