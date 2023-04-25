package com.peasenet.main;

public class QueenList {
    private Queen[] queens;
    private int size;

    public QueenList(int size) {
        this.size = size;
        queens = new Queen[size];
    }

    public Queen getQueen(int index) {
        return queens[index];
    }

    public void setQueen(int index, Queen queen) {
        queens[index] = queen;
    }

    public int getSize() {
        return size;
    }

    public boolean equals(QueenList other) {
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.queens[i].equals(other.queens[i])) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            if (queens[i] != null)
                result += queens[i].toString() + " ";
        }
        if (isValid()) {
            result += "Valid";
        } else {
            result += "Invalid";
        }
        return result;
    }

    public boolean isValid() {
        // check if the number of queens is equal to the size of the board
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (queens[i] != null) {
                count++;
            }
        }
        return count == size;
    }

    public int getPlacedQueens() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (queens[i] != null) {
                count++;
            }
        }
        return count;
    }

    public QueenList(QueenList queens) {
        this.size = queens.size;
        this.queens = new Queen[size];
        for (int i = 0; i < size; i++) {
            this.queens[i] = queens.queens[i];
        }
    }
}
