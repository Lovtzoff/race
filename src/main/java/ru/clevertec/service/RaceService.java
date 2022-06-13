package ru.clevertec.service;

import ru.clevertec.model.Car;
import ru.clevertec.model.CarThread;

import java.util.concurrent.CountDownLatch;

/**
 * Класс RaceService.
 */
public class RaceService {

    //Создаем CountDownLatch на 8 "условий"
    private static final CountDownLatch START = new CountDownLatch(8);

    /**
     * Запуск гонки.
     *
     * @param numberOfCars кол-во автомобилей
     * @param trackLength  длина трека
     * @throws InterruptedException the interrupted exception
     */
    public void raceStart(int numberOfCars, int trackLength) throws InterruptedException {
        for (int i = 0; i < numberOfCars; i++) {
            new Thread(new CarThread(START, trackLength, new Car(i, (int) (Math.random() * 100 + 50)))).start();
            Thread.sleep(1000);
        }

        while (START.getCount() > 3) {  //Проверяем, собрались ли все автомобили
            Thread.sleep(100);    //у стартовой прямой. Если нет, ждем 100ms
        }

        Thread.sleep(1000);
        System.out.println("На старт!");
        START.countDown();  //Команда дана, уменьшаем счетчик на 1
        Thread.sleep(1000);
        System.out.println("Внимание!");
        START.countDown();  //Команда дана, уменьшаем счетчик на 1
        Thread.sleep(1000);
        System.out.println("Марш!");
        START.countDown();  //Команда дана, уменьшаем счетчик на 1
        //счетчик становится равным нулю, и все ожидающие потоки
        //одновременно раpблокируются
    }
}
