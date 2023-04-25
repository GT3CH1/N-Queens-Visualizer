package gui;

import com.peasenet.main.Main;
import com.peasenet.main.Queen;
import com.peasenet.main.QueenList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionPanel extends JPanel {
    private static int BOARD_DIMENSION = 4;

    private static boolean animated = false;

    public SolutionPanel() {
        this.setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public static boolean isAnimated() {
        return animated;
    }

    public static void setAnimated(boolean animated) {
        SolutionPanel.animated = animated;
    }


    public void setDimension(int dimension) {
        BOARD_DIMENSION = dimension;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoard(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintBoard(g);
    }

    public void paintBoardAnimated(Graphics g) {
        solver(new QueenList(BOARD_DIMENSION), 0, g);
    }

    private void solver(QueenList queens, int row, Graphics g) {
        paintBoard(g);
        if (row == queens.getSize()) {
            Main.solutions.add(new QueenList(queens));
            return;
        }
        for (int i = 0; i < queens.getSize(); i++) {
            if (Main.isSafe(queens, row, i)) {
                queens.setQueen(row, new Queen(row, i));
                int width = this.getWidth();
                int height = this.getHeight();
                int cellWidth = width / BOARD_DIMENSION;
                int cellHeight = height / BOARD_DIMENSION;
//                paintQueens(g, cellWidth, cellHeight,queens);
                solver(queens, row + 1, g);
                queens.setQueen(row, null);
            }
        }
    }

    public void paintBoard(Graphics g) {
        if (BOARD_DIMENSION == 0)
            return;
        g.setColor(Color.BLACK);
        // draw a 7x7 grid, scaled to fit the panel
        int width = this.getWidth();
        int height = this.getHeight();
        int cellWidth = width / BOARD_DIMENSION;
        int cellHeight = height / BOARD_DIMENSION;
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                g.drawRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
            }
        }
        // fill every other cell with a light gray
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < BOARD_DIMENSION; i++) {
            for (int j = 0; j < BOARD_DIMENSION; j++) {
                if ((i + j) % 2 == 0) {
                    g.fillRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
                }
            }
        }
        // check if solutionIndex is valid
        if (Main.solutions.size() == 0)
            return;
        paintQueens(g, cellWidth, cellHeight, Main.solutions.get(NQueensWindow.solutionIndex));
    }

    private static void paintQueens(Graphics g, int cellWidth, int cellHeight, QueenList validSolution) {
        for (int i = 0; i < validSolution.getSize(); i++) {
            g.setColor(Color.RED);
            g.fillOval(validSolution.getQueen(i).getCol() * cellWidth, validSolution.getQueen(i).getRow() * cellHeight, cellWidth, cellHeight);
        }
    }

    public static int getBoardDimension() {
        return BOARD_DIMENSION;
    }
}
