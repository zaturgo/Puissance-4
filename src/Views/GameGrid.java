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
        long bitboard = _grid.getBitboardPlayer1();
        for(int i = 0; i < 48; i ++) {
            if((bitboard>>i)%2 == 1) {
                System.out.println("Vrai pour : " + i);
                int xa = i / 7;
                int ya = i % 7;
                g.setColor(Color.red);
                g.fillOval(xa * 64, (GridUtils.NbLine - ya - 1) * 64, 64, 64);
            }
        }
        long bitboard2 = _grid.getBitboardPlayer2();
        for(int i = 0; i < 48; i ++) {
            if((bitboard2>>i)%2 == 1) {
                System.out.println("Vrai pour : " + i);
                int xa = i / 7;
                int ya = i % 7;
                g.setColor(Color.blue);
                g.fillOval(xa * 64, (GridUtils.NbLine - ya - 1) * 64, 64, 64);
            }
        }

    }

}