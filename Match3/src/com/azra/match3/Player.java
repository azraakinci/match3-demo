package com.azra.match3;

public class Player {
    private int row;
    private int col;
    private char symbol;

    public Player(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getSymbol() {
        return symbol;
    }

    public void moveUp() {
        if (row > 0) {
            row--;
        }
    }

    public void moveDown(int maxRows){
        if(row < maxRows - 1){
            row++;
        }
    }

    public void moveRight(int maxCols){
        if(col < maxCols - 1){
            col++;
        }
    }

    public void moveLeft(){
        if(col > 0){
            col--;
        }
    }


}
