package com.azra.match3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Grid grid = new Grid(5,5);


        boolean running = true;

        while (running){
            grid.printGrid();
            System.out.println("W/A/S/D ile hareket et, Q ile çıkış yap, E ile taşını seç:");
            String input = scanner.nextLine().toUpperCase();


            switch (input){
                case "W":
                    grid.moveUp();break;
                case "S":
                    grid.moveDown(); break;
                case "A":
                    grid.moveLeft(); break;
                case "D":
                    grid.moveRight(); break;
                case "Q": running = false; break;
                case "E":
                    grid.select();break;
            }
            System.out.println();
        }
        scanner.close();
        System.out.println("Oyun bitti!");

    }


}
