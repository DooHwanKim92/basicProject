package org.example;


public class Main {
    public static void main(String[] args) {
        Container.initSc();

        new App().run();

        Container.closeSc();
    }
}