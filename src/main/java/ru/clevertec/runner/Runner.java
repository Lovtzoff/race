package ru.clevertec.runner;

import ru.clevertec.service.RaceService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс Runner.
 */
public class Runner {

    /**
     * Пуск.
     */
    public void run() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Введите кол-во автомобилей: ");
            String numberOfCars  = bufferedReader.readLine();

            System.out.print("Введите длину трассы: ");
            String trackLength = bufferedReader.readLine();

            try {
                new RaceService().raceStart(Integer.parseInt(numberOfCars), Integer.parseInt(trackLength));
            } catch (NumberFormatException e) {
                System.out.println("Возникла проблема преобразований");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Возникла проблема чтения из консоли");
        }
    }
}
