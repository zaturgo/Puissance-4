package Views;

import Models.Grid;

import javax.swing.*;
import java.awt.*;

public class GameGrid extends JPanel {

    private Grid _grid;

    public GameGrid(Grid grid) {
        _grid = grid;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = 64;

        int y = 0;
        for (int horz = 0; horz < 7; horz++) {
            int x = 0;
            for (int vert = 0; vert < 7; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        for (int i = 6; i >= 0; i--) {
            for(int j = 0; j < 7; j++) {
                if(_grid.getTokens()[i][j] == 1) {
                    g.setColor(Color.red);
                    g.fillOval(j * 64, i * 64, 64, 64);
                }
                else if(_grid.getTokens()[i][j] == 2) {
                    g.setColor(Color.blue);
                    g.fillOval(j * 64, i * 64, 64, 64);
                }
            }
        }
    }

}