package com.gorichko.andrew.encryptors;

import com.gorichko.andrew.Main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Cesar {

    private String alphabetB = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
    private String alphabetL = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    public static String finalString;
    public static int key;

    public void HashCodeCesar(){
        System.out.println("");
        System.out.println(alphabetB + alphabetL);
        System.out.println("");
        System.out.println("Шифр Цезаря");
        System.out.println(" 1. Шифрування");
        System.out.println(" 2. Дешифрування");
        System.out.println(" 3. Злом шифру");
        System.out.println(" 4. Повернутись в меню");

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        switch (number) {
            case 1:
                System.out.println("Введіть текст:");
                Scanner scanner1 = new Scanner(System.in);
                String s = scanner1.nextLine();
                System.out.println("Введіть ключ:");
                Scanner scanner2 = new Scanner(System.in);
                key = scanner2.nextInt();

                inputTextCodeCesar(s, key);

                System.out.println("");
                Cesar cesar = new Cesar();
                cesar.HashCodeCesar();
            case 2:
                System.out.println("Введіть ключ:");
                Scanner scanner3 = new Scanner(System.in);
                int unLockKey = scanner3.nextInt();
                System.out.println("");

                if (key == unLockKey) {
                    System.out.println("Зашифрований текст:");
                    try(FileReader reader = new FileReader("cypher1.txt"))
                    {
                        char[] buf = new char[256];
                        int c;
                        while((c = reader.read(buf))>0){
                            if(c < 256){
                                buf = Arrays.copyOf(buf, c);
                            }
                            System.out.print(buf);
                        }
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }

                    System.out.println("");
                    System.out.println("Розшифрований текст:");
                    System.out.println(finalString);
                }

                System.out.println("");

                Cesar cesar1 = new Cesar();
                cesar1.HashCodeCesar();
            case 3:
                System.out.println("");
                System.out.println("Запущено процес розшифрування слова (взлом)...");
                System.out.println("Розшифрований текст:");
                for (int i=1; i<33; i++) {
                    HackingTextCodeCesar(sText, i);
                }
                System.out.println("");

                Cesar cesar2 = new Cesar();
                cesar2.HashCodeCesar();
            case 4:
                Main main = new Main();
                main.init();
                break;
        }

    }

    public String CodeCesar(String input, int key){
        String shifrAlphabetB = alphabetB.substring(key) + alphabetB.substring(0, key);
        String shifrAlphabetL = alphabetL.substring(key) + alphabetL.substring(0, key);
        String s = "";

        for (int i = 0; i < input.length(); i++){
            if (Character.isLetter(input.charAt(i)) && Character.isLowerCase(input.charAt(i))){
                char ch = input.charAt(i);
                int pos = alphabetL.indexOf(ch);
                s = s + shifrAlphabetL.charAt(pos);
            }
            else if (Character.isLetter(input.charAt(i)) && Character.isUpperCase(input.charAt(i))){
                char ch = input.charAt(i);
                int pos = alphabetB.indexOf(ch);
                s = s + shifrAlphabetB.charAt(pos);
            }
            else {
                s = s + input.charAt(i);
            }
        }

        try(FileWriter writer = new FileWriter("cypher1.txt", false)){
            writer.write(s);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return  s;
    }

    public static String sText;

    public void inputTextCodeCesar(String s, int key){
        finalString = s;
        if (key < 0){
            System.out.println("Key < 0. Помилка!");
        }
        else {
            sText = CodeCesar(s, key);
            System.out.println(sText);
        }
    }

    public void HackingTextCodeCesar(String s, int key){
        if (key < 0){
            System.out.println("Key < 0. Помилка!");
        }
        else {
            String resText = CodeCesar(s, key);
            System.out.println(resText);

            if (finalString.equals(resText)){
                try(FileWriter writer = new FileWriter("open1.txt", false)){
                    writer.write(resText);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
