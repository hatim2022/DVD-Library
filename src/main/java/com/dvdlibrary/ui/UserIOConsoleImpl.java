package com.dvdlibrary.ui;

import java.io.IOException;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    private Scanner scanner=new Scanner(System.in);
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    @Override
    public String readString(String prompt,int min,int max) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine();
        }while (Integer.parseInt(input)<min || Integer.parseInt(input)>max);
        return input;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int x;
        do {
            System.out.println(prompt);
            x=scanner.nextInt();
        }while (x<min || x>max);
        return x;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return scanner.nextDouble();
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double x;
        do {
            System.out.println(prompt);
            x=scanner.nextInt();
        }while (x<min || x>max);
        return x;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return scanner.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float x;
        do {
            System.out.println(prompt);
            x=scanner.nextInt();
        }while (x<min || x>max);
        return x;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return scanner.nextLong();
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long x;
        do {
            System.out.println(prompt);
            x=scanner.nextInt();
        }while (x<min || x>max);
        return x;
    }

    @Override
    public void readAny() throws IOException {
        scanner.nextLine();
    }
}
