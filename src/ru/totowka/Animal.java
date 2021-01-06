package ru.totowka;

/**
 * Класс животного. Отвечает за параметры рака, лебедя и щуки.
 *
 * @author <a href="mailto:tskocharyan@edu.hse.ru"> Tigran Kocharyan</a>
 */
public class Animal {
    private final double angle;
    private final double coef;
    private final String name;

    /**
     * Создаем животного с параметрами.
     *
     * @param angle угол направления движения животного.
     * @param name  имя животного.
     */
    public Animal(int angle, String name) {
        if (name == null) {
            throw new NullPointerException("The given name is invalid!");
        }
        this.angle = Math.toRadians(angle);
        this.name = name;
        this.coef = Utils.getRandom(1, 10);
    }

    /**
     * @return Возвращает угол животного.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @return Коэф-т животного.
     */
    public double getCoef() {
        return coef;
    }

    /**
     * @return Имя животного.
     */
    @Override
    public String toString() {
        return name;
    }
}
