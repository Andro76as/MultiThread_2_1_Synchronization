package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int carCount = 5;
    private final List<Car> cars = new ArrayList<>();

    // synchronized void receiveCar
    public void receiveCar() {
        for (int i = 0; i < carCount; i++) {
            try {
                Thread.sleep(1500);
                cars.add(new Car(this));
                System.out.println(Thread.currentThread().getName() + " Toyota выпустил 1 авто.");
                synchronized (this) {
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (cars.size() == 0) {
                System.out.println("Машин нет!");
                wait();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто.");
            cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
