package ru.netology;

public class Main {

    public static final int buyers = 8;

    public static void main(String[] args) {
        final Dealer dealer = new Dealer();
        for (int i = 1; i <= buyers; i++) {
            new Thread(null, dealer::sellCar, "Покупатель " + i).start();
        }
        new Thread(null, dealer::receiveCar, "Производитель").start();
    }

}
