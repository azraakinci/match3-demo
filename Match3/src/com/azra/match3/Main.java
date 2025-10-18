package com.azra.match3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Grid grid = new Grid(5,5);
        Player player = new Player(2,2,'X');

        grid.setCell(player.getRow(), player.getCol(), player.getSymbol());

        boolean running = true;

        while (running){
            grid.printGrid();
            System.out.println("W/A/S/D ile hareket et, Q ile çıkış yap:");
            String input = scanner.nextLine().toUpperCase();

            grid.setCell(player.getRow(), player.getCol(), '.');

            switch (input){
                case "W":
                    player.moveUp();break;
                case "S":
                    player.moveDown(5); break;
                case "A":
                    player.moveLeft(); break;
                case "D":
                    player.moveRight(5); break;
                case "Q": running = false; break;
            }
            grid.setCell(player.getRow(), player.getCol(), player.getSymbol());
            System.out.println();
        }
        scanner.close();
        System.out.println("Oyun bitti!");

    }


}
