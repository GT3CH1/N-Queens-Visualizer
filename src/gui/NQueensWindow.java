package gui;

import com.peasenet.main.Main;

import javax.swing.*;
import java.beans.ParameterDescriptor;

public class NQueensWindow extends JFrame {
    private JPanel panel1;
    private JButton nextSolution;
    private JButton previousSolution;
    private JSpinner dimensionSize;
    private JButton generateButton;
    private JLabel totalSolutionCount;
    private JLabel currentSolutionIdx;
    private SolutionPanel jPanel2;
    protected static int solutionIndex = 0;

    public NQueensWindow(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        this.setSize(500, 500);
        this.setVisible(true);
        setResizable(false);
        createUIComponents();
        jPanel2.setDimension(SolutionPanel.getBoardDimension());
        Main.solve(SolutionPanel.getBoardDimension());
        this.currentSolutionIdx.setText(String.valueOf(1));
        this.totalSolutionCount.setText(String.valueOf(Main.solutions.size()));
        repaint();
    }

    private void createUIComponents() {
        jPanel2 = new SolutionPanel();
        nextSolution.addActionListener(e -> {
            solutionIndex++;
            if (solutionIndex >= Main.solutions.size())
                solutionIndex = Main.solutions.size() - 1;
            this.currentSolutionIdx.setText(String.valueOf(solutionIndex + 1));
            repaint();
        });
        previousSolution.addActionListener(e -> {
            solutionIndex--;
            if (solutionIndex < 0)
                solutionIndex = 0;
            this.currentSolutionIdx.setText(String.valueOf(solutionIndex + 1));
            repaint();
        });
        generateButton.addActionListener(e -> {
            solutionIndex = 0;
            int dim = (int) dimensionSize.getValue();
            jPanel2.setDimension(dim);
            Main.solve(dim);
            this.totalSolutionCount.setText(String.valueOf(Main.solutions.size()));
            this.currentSolutionIdx.setText(String.valueOf(1));

            repaint();
        });
        dimensionSize.setValue(4);
        ((SpinnerNumberModel) dimensionSize.getModel()).setMaximum(10);
        ((SpinnerNumberModel) dimensionSize.getModel()).setMinimum(1);
    }
}
