package Views;

import Models.Grid;
import Utils.GridUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
        for (int horz = 0; horz < GridUtils.NbLine; horz++) {
            int x = 0;
            for (int vert = 0; vert < GridUtils.NbCol; vert++) {
                g.drawRect(x, y, size, size);
                x += size;
            }
            y += size;
        }
        ArrayList<Integer> tokens[] = _grid.newGetTokens();
        for(int j = 0; j < GridUtils.NbCol; j++) {
            for(int i = 0; i < tokens[j].size(); i++) {
                if(tokens[j].get(i) == 1) {
                    g.setColor(Color.red);
                }
                else if(tokens[j].get(i) == 2) {
                    g.setColor(Color.blue);
                }
                g.fillOval(j * 64, (GridUtils.NbLine - i - 1) * 64, 64, 64);
            }
        }
    }

}