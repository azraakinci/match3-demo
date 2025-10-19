package com.azra.match3;

import java.util.Random;

public class Grid {

    private int cursorX = 0;
    private int cursorY = 0;

    private int selectedX = -1;
    private int selectedY = -1; //kullanıcıının seçtiği tuşu tutcak

    private char [][] cells;
    //iki boyutlu char dizisi tutuyo
    private int rows;
    private int cols;
    Random rand = new Random();
    int tasTurSayisi = 3; // 'A' 'B' 'C' olsun

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new char[rows][cols];
        //başlangıçta tüm hücreleri boş istenen grid oluşuyor
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int rastgeleSayi = rand.nextInt(tasTurSayisi);
                cells[i][j] = (char)('A' + rastgeleSayi);
            }
        }
    }


    public char getCell(int row, int col){
        return cells[row][col];
        //istenen hücreyi döndürür
    }

    public void moveUp() {
        if (cursorY > 0) {
            cursorY--;
        }
    }

    public void moveDown(){
        if(cursorY < rows - 1){
            cursorY++;
        }
    }

    public void moveRight(){
        if(cursorX < cols - 1){
            cursorX++;
        }
    }

    public void moveLeft(){
        if(cursorX > 0){
            cursorX--;
        }
    }


    public void printGrid(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

                if(i == selectedY && j == selectedX){
                    System.out.print("{" + cells[i][j] + "}");
                }

                else if (i == cursorY && j == cursorX) {
                    System.out.print("[" + cells[i][j] + "]");
                }
                else {
                    System.out.print(" " + cells[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void select(){

        if(selectedX == -1){
            selectedX = cursorX; //artık -1 değil
            selectedY = cursorY;
            System.out.println("Seçili taş konumu: " + "(" + cursorX + ") " + "(" + cursorY + ")" );

        }
        else {
            int xDiff = Math.abs(cursorX - selectedX);
            int yDiff = Math.abs(cursorY - selectedY);

            if (xDiff + yDiff == 1){
                System.out.println("Komşu taş seçildi. Değişim yapılıyor...");

                char temp = cells[selectedY][selectedX];
                cells[selectedY][selectedX] = cells[cursorY][cursorX];
                cells[cursorY][cursorX] = temp;
            }

            else {
                System.out.println("Komşu değiller! Seçim iptal edildi");
            }

            selectedX = -1;
            selectedY = -1;
        }


    }


}
