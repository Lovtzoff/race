package ru.clevertec.model;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * Класс потока автомобиля.
 */
@Slf4j
public class CarThread implements Runnable {

    private final CountDownLatch START; // замок с обратным отсчетом
    private Integer trackLength;
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
            log.info("Автомобиль №" + car.getNumber() + " подъехал к стартовой прямой.");
            //Автомобиль подъехал к стартовой прямой — условие выполнено
            //уменьшаем счетчик на 1.
            START.countDown();
            //метод await() блокирует поток, вызвавший его, до тех пор, пока
            //счетчик CountDownLatch не станет равен 0.
            START.await();
            Thread.sleep(1000);

            // Каждую секунду логируем сколько до финиша.
            while (trackLength > 0) {
                log.info("Автомобилю №" + car.getNumber() + " осталось до финиша " + trackLength + ".");
                trackLength -= car.getSpeed();
                Thread.sleep(1000);
            }

            log.info("Автомобиль №" + car.getNumber() + " финишировал!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
