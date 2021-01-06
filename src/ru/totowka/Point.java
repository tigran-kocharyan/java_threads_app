package ru.totowka;

import java.util.Objects;

/**
 * Класс точки для хранения координат.
 *
 * @author <a href="mailto:tskocharyan@edu.hse.ru"> Tigran Kocharyan</a>
 */
public class Point {
    private double x;
    private double y;

    /**
     * Создание точки с координатами
     *
     * @param x координата на оси X
     * @param y координата на оси Y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Добавление к текущим координатам сдвига.
     *
     * @param deltaX сдвиг на оси X.
     * @param deltaY сдвиг на оси Y.
     */
    public void add(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * @return представление координат в виде строки.
     */
    @Override
    public String toString() {
        return String.format("(%.2f ; %.2f)", x, y);
    }

    /**
     * Переопределение equals для двух координат.
     *
     * @param object объект сравнения
     * @return true - если равны, false - если не равны.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Point point = (Point) object;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    /**
     * Переопределение equals для двух координат.
     *
     * @return хэшкод x и y.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
