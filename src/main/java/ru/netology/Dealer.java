package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int carCount = 7;
    private static final int recieveTime = 1000;
    private static final int sellTime = 1000;
    private final List<Car> cars = new ArrayList<>();

    public void receiveCar() {
        for (int i = 0; i < carCount; i++) {
            try {
                System.out.println("Производитель toyota выпустил 1 авто");
                Thread.sleep(recieveTime);
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
            Thread.sleep(sellTime);
            System.out.println("Покупатель " + Thread.currentThread().getName() + " уехал на новеньком авто");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
