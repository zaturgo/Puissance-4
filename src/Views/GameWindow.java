package Views;

import Models.Grid;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        this.setTitle("Puissance 4");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void update() {
        this.repaint();
    }
}
