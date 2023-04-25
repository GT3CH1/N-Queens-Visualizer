package com.peasenet.main;

import gui.NQueensWindow;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static ArrayList<QueenList> solutions;
    public static NQueensWindow window;

    public static void main(String[] args) {
        solutions = new ArrayList<>();
        window = new NQueensWindow("N-Queens Visualizer");
    }

    public static void solve(int dimension) {
        QueenList queens = new QueenList(dimension);
        solutions = new ArrayList<>();
        solver(queens, 0);
    }

    public static void solver(QueenList queens, int row) {
        if (row == queens.getSize()) {
            solutions.add(new QueenList(queens));
            return;
        }
        for (int i = 0; i < queens.getSize(); i++) {
            if (isSafe(queens, row, i)) {
                queens.setQueen(row, new Queen(row, i));
                solver(queens, row + 1);
                queens.setQueen(row, null);
            }
        }
    }

    public static boolean isSafe(QueenList list, int row, int column) {
        // check if the current position is safe by checking if there are any queens in the same row, column, or diagonal
        for (int i = 0; i < list.getSize(); i++) {
            if (list.getQueen(i) == null)
                continue;
            if (list.getQueen(i).getRow() == row || list.getQueen(i).getCol() == column)
                return false;
            if (Math.abs(list.getQueen(i).getRow() - row) == Math.abs(list.getQueen(i).getCol() - column))
                return false;
        }
        return true;
    }
}
