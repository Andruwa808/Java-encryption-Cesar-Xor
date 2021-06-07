package com.gorichko.andrew;

import com.gorichko.andrew.encryptors.Cesar;
import com.gorichko.andrew.encryptors.Xor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        Cesar cesar = new Cesar();
        Xor xor = new Xor();

        System.out.println("Виберіть метод:");
        System.out.println(" 1. Цезарь");
        System.out.println(" 2. XOR");
        System.out.println(" 3. Завершити роботу програми");
        System.out.println("");

        System.out.println("Введіть цифру (1-3): ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        switch (number){
            case 1:
                cesar.HashCodeCesar();
                break;
            case 2:
                xor.HashCodeXor();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Такого метода не існує, будь уважнішим!");
                System.out.println("Спробуй знову ;)");
                System.out.println("");
                Main main = new Main();
                main.init();
                break;
        }
    }
}

