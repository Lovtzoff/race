package ru.clevertec.model;

import java.util.concurrent.CountDownLatch;

/**
 * Класс потока автомобиля.
 */
public class CarThread implements Runnable {

    private final CountDownLatch START; // замок с обратным отсчетом
    private final Integer trackLength;
    private final Car car;

    /**
     * Конструктор.
     *
     * @param start       замок
     * @param trackLength длина трека
     * @param car         автомобиль
     */
    public CarThread(CountDownLatch start, Integer trackLength, Car car) {
        START = start;
        this.trackLength = trackLength;
        this.car = car;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Автомобиль №%d подъехал к стартовой прямой.\n", car.getNumber());
            //Автомобиль подъехал к стартовой прямой - условие выполнено
            //уменьшаем счетчик на 1
            START.countDown();
            //метод await() блокирует поток, вызвавший его, до тех пор, пока
            //счетчик CountDownLatch не станет равен 0
            START.await();
            Thread.sleep(trackLength / car.getSpeed()); //ждем пока проедет трассу
            System.out.printf("Автомобиль №%d финишировал!\n", car.getNumber());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
