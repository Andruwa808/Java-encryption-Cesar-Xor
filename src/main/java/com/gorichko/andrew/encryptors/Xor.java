package com.gorichko.andrew.encryptors;

import com.gorichko.andrew.Main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Xor {
    public String text;
    public String key;
    public static String codeHex = "";


    public void HashCodeXor() {
        System.out.println("XOR-шифрування");
        System.out.println(" 1. Шифрування");
        System.out.println(" 2. Дешифрування");
        System.out.println(" 3. Повернутись в меню");

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        switch (number) {
            case 1:
                System.out.println("Введіть текст:");
                Scanner scanner1 = new Scanner(System.in);
                text = scanner1.nextLine();

                System.out.println("Введіть ключ:");
                Scanner scanner2 = new Scanner(System.in);
                key = scanner2.nextLine();

                CodeXor(text, key);

                Xor xor = new Xor();
                xor.HashCodeXor();
            case 2:
                System.out.println("Введіть ключ: ");
                Scanner scanner3 = new Scanner(System.in);
                String unLock = scanner3.nextLine();
                UnCodeXor(codeHex, unLock);

                Xor xor1 = new Xor();
                xor1.HashCodeXor();
            case 3:
                Main main = new Main();
                main.init();
                break;
        }
    }

    public static void CodeXor(String text, String key) {
        int keyItr = 0;
        for (int i = 0; i < text.length(); i++) {
            int temp = text.charAt(i) ^ key.charAt(keyItr);
            codeHex += String.format("%02x", (byte)temp);
            keyItr++;
            if(keyItr >= key.length()){
                keyItr = 0;
            }
        }
        System.out.println("Зашифрований текст: ");
        System.out.println(codeHex);

        try(FileWriter writer = new FileWriter("cypher2.txt", false)){
            writer.write(codeHex);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("");
    }

    public static void UnCodeXor(String codeHex, String unLock){
        System.out.println("");
        System.out.println("Считую фалй...");

        try(FileReader reader1 = new FileReader("cypher2.txt"))
        {
            char[] codeReader = new char[256];
            int c;
            while((c = reader1.read(codeReader))>0){
                if(c < 256){
                    codeReader = Arrays.copyOf(codeReader, c);
                }
                System.out.print(codeReader);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        String hex = "";
        for (int i = 0; i < codeHex.length()-1; i+=2) {
            String output = codeHex.substring(i, (i+2));
            int decimal = Integer.parseInt(output, 16);
            hex += (char)decimal;
        }

        String backText = "";
        int keyItr = 0;
        for (int i = 0; i < hex.length(); i++) {
            int temp = hex.charAt(i) ^ unLock.charAt(keyItr);

            backText = backText + (char)temp;
            keyItr++;
            if(keyItr >= unLock.length()){
                keyItr = 0;
            }
        }

        System.out.println("");
        System.out.println("Розшифрований текст:");
        System.out.println(backText);

        try(FileWriter writer = new FileWriter("open2.txt", false)){
            writer.write(backText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("");
    }
}
