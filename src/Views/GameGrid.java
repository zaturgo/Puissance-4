package Views;

import Models.Grid;
import Utils.GridUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameGrid extends JPanel {

    private Grid _grid;

    public static int lastClickedCol = 0;

    public GameGrid(Grid grid) {
        _grid = grid;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameGrid.lastClickedCol = e.getX()/64;
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = 64;

        int y = 0;
        for (int horz = 0; horz < 6; horz++) {
            int x = 0;
            for (int vert = 0; vert < 7; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        for (int i = GridUtils.NbLine-1; i >= 0; i--) {
            for(int j = 0; j < GridUtils.NbCol; j++) {
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