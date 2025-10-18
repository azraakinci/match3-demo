package com.azra.match3;

public class Grid {
    private char [][] cells;
    //iki boyutlu char dizisi tutuyo
    private int rows;
    private int cols;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new char[rows][cols];
        //başlangıçta tüm hücreleri boş istenen grid oluşuyor
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = '.';
            }
        }
    }

    public void setCell (int row, int col, char c){
        cells[row][col] = c;
        //istediğin karakterden oluşan hücre oluşturur
    }

    public char getCell(int row, int col){
        return cells[row][col];
        //istenen hücreyi döndürür
    }


    public void printGrid(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }


}
