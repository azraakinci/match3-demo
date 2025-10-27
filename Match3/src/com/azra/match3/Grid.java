package com.azra.match3;

import java.util.Random;

public class Grid {

    private int score = 0;
    private boolean isGameOver = false;

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
                char newStone;
                boolean isMatch;

                do {
                    int randomNumber = rand.nextInt(tasTurSayisi);
                    newStone = (char) ('A' + randomNumber);
                    isMatch = false; //varsayılan olarak ayarlayalım

                    if(j >= 2 && cells[i][j-1] == newStone && cells[i][j-2] == newStone){
                        isMatch = true; //2.sütundan büyük müyüz diye bakıcaz değilsek zaten bi önceki 2 sütunumuzla eşleşmiyoruz demektir yatayda

                    }

                    if(i >= 2 && cells[i - 1][j] == newStone && cells[i - 2][j] == newStone){
                        isMatch = true;
                    }

                }while(isMatch);
                cells[i][j] = newStone;

            }
        }
    }


    public char getCell(int row, int col){
        return cells[row][col];
        //istenen hücreyi döndürür
    }

    public int getScore(){
        return score;
    }

    public boolean isGameOver() {
        return isGameOver;
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

        System.out.println("====================");
        System.out.println(" SKOR: " + this.score);
        System.out.println("====================");

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

                if(i == selectedY && j == selectedX){
                    System.out.print("{" + cells[i][j] + "}");
                }

                else if (i == cursorY && j == cursorX) {
                    System.out.print("[" + cells[i][j] + "]");
                }

                else if (cells[i][j] == ' ') {
                    System.out.print(" . ");
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

            if (xDiff + yDiff == 1) {
                System.out.println("Komşu taş seçildi. Değişim yapılıyor...");

                char temp = cells[selectedY][selectedX];
                cells[selectedY][selectedX] = cells[cursorY][cursorX];
                cells[cursorY][cursorX] = temp;

                while (checkMatches()) {

                    printGrid();
                    try {
                        Thread.sleep(2000);
                        //yarım saniye bekletiyoz
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    applyGravity();
                    refillBoard();
                    printGrid();
                    //alttaki yapı yazdırılmış kodu yarım saniye bekletmeye yarıyo
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (score >= 100) {
                    System.out.println("Skorunuz: " + score + " Oyunu kazandınız!");
                    this.isGameOver = true; // Oyunu "bitti" olarak işaretle.
                }
            }
            else {
                System.out.println("Komşu değiller! Seçim iptal edildi");
            }

            selectedX = -1;
            selectedY = -1;
        }
    }

    public boolean checkMatches(){
        System.out.println("Eşleşmeler kontrol ediliyor...");
        boolean eslesmeDurumu = false;

        for(int i = 0; i < rows ; i++){
            for(int j = 0; j < cols - 2; j++){
                char stone = cells[i][j];
                if(stone != ' ' && stone == cells[i][j+1] && stone == cells[i][j+2]){
                    System.out.println("Yatay eşleşme bulundu!");
                    cells[i][j] = ' ';
                    cells[i][j + 1] = ' ';
                    cells[i][j + 2] = ' ';
                    eslesmeDurumu = true;
                    score += 10;
                }
            }
        }

        for(int c = 0; c < cols ; c++){
            for(int r = 0; r < rows - 2 ; r++){
                char stone = cells[r][c];

                if(stone != ' ' && stone == cells[r + 1][c] && stone == cells[r + 2][c]){
                    System.out.println("Dikey eşleşme bulundu!");
                    cells[r][c] = ' ';
                    cells[r + 1][c] = ' ';
                    cells[r + 2][c] = ' ';
                    eslesmeDurumu = true;
                    score += 10;
                }
            }
        }
        return eslesmeDurumu;
    }

    public void applyGravity(){
        System.out.println("Boşluklar dolduruluyor...");

        for(int c = 0; c < cols; c++){

            int firstEmptyRow = -1;

            for(int r = rows - 1; r >= 0; r--){

                char stone = cells[r][c];

                if(stone == ' ' || stone == '.'){
                   if(firstEmptyRow == -1){
                       firstEmptyRow = r;
                   }
                }
                else if (firstEmptyRow != -1){//burada kayıtlı taş var bi üstte de normal tas
                    cells[firstEmptyRow][c] = stone; //boş olmayan ilk taşı bulduğumuzda düşürdük
                    cells[r][c]= ' ';
                    firstEmptyRow--;

                }
            }
        }
    }

    public void refillBoard(){
        System.out.println("Tahta yeniden dolduruluyo");
        for(int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){

                if(cells[r][c] == ' ' || cells[r][c] == '.'){
                    int randomStone = rand.nextInt(this.tasTurSayisi);
                    cells[r][c] = (char)('A' + randomStone);
                }
            }
        }
    }




}
