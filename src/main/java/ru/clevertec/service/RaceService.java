package ru.clevertec.service;

import ru.clevertec.model.Car;
import ru.clevertec.model.CarThread;

import java.util.concurrent.CountDownLatch;

/**
 * Класс RaceService.
 */
public class RaceService {

    // Количество команд для запуска гонки (команды: На старт, Внимание, Марш)
    private final int numberOfCommand = 3;

    /**
     * Запуск гонки.
     *
     * @param numberOfCars кол-во автомобилей
     * @param trackLength  длина трека
     * @throws InterruptedException the interrupted exception
     */
    public void raceStart(int numberOfCars, int trackLength) throws InterruptedException {
        // Создаем CountDownLatch на кол-во условий = (кол-во машин + кол-во команд)
        CountDownLatch START = new CountDownLatch(numberOfCars + numberOfCommand);

        for (int i = 0; i < numberOfCars; i++) {
            new Thread(new CarThread(START, trackLength, new Car(i, (int) (Math.random() * 100 + 50)))).start();
            Thread.sleep(1000);
        }

        while (START.getCount() > numberOfCommand) {  //Проверяем, собрались ли все автомобили
            Thread.sleep(100);                  //у стартовой прямой. Если нет, ждем 100ms
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
