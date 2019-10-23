package Views;

import Models.Grid;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(Grid grid) {
        this.setTitle("Puissance 4");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(new GameGrid(grid));
    }
    public void update() {
        this.repaint();
    }
}
