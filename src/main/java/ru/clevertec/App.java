package ru.clevertec;

import ru.clevertec.runner.Runner;

/**
 * Основной класс приложения.
 */
public class App {

    /**
     * Точка запуска приложения.
     *
     * @param args массив входных параметров
     */
    public static void main(String[] args) {

        new Runner().run();
    }
}