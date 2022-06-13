package ru.clevertec.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Класс автомобиля.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Car {

    /**
     * Номер автомобиля
     */
    private int number;
    /**
     * Скорость
     */
    private int speed;

    @Override
    public String toString() {
        return "Car{" +
                "number = " + number +
                ", speed = " + speed +
                '}';
    }
}
