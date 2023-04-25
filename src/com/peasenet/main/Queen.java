package com.peasenet.main;

public class Queen {
    private int row;
    private int col;
    
    public Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public void setRow(int row) {
        this.row = row;
    }
    
    public void setCol(int col) {
        this.col = col;
    }
    
    public boolean equals(Queen other) {
        return this.row == other.row && this.col == other.col;
    }
    
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
