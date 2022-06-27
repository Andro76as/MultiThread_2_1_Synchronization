package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int CAR_COUNT = 7;
    private static final int RECIEVE_TIME = 1000;
    private static final int SELL_TIME = 1000;
    private final List<Car> cars = new ArrayList<>();

    public void receiveCar() {
        for (int i = 0; i < CAR_COUNT; i++) {
            try {
                System.out.println("Производитель toyota выпустил 1 авто");
                Thread.sleep(RECIEVE_TIME);
                synchronized (this) {
                    cars.add(new Car());
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sellCar() {
        System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
        try {
            while (cars.size() == 0) {
                System.out.println("Машин нет");
                wait();
            }
            Thread.sleep(SELL_TIME);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новеньком авто");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
