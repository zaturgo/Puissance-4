package Views;

import Controllers.Game;
import Models.Grid;
import Models.Human;
import Utils.GridUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameGrid extends JPanel {

    private Grid _grid;
    private JLabel _tour = new JLabel("",SwingConstants.RIGHT);
    private JButton _save;
    private JButton _menu;
    private MouseAdapter _ma;
    private Human _human;

    public static int lastClickedCol = 0;

    public GameGrid() {
        _grid = Game.getGame().getGrid();
        _save = new JButton("Sauvegarder");
        _menu = new JButton("Retour au menu");
        _menu.addActionListener(this::actionPerformed);
        _save.addActionListener(this::save);
        this.add(_tour);
        this.setBorder(BorderFactory.createEmptyBorder(0, 350, 0,0));
        this.add(_save);
        this.add(_menu);
        _tour.setFont(new Font("Arial",Font.BOLD,22));
        this.add(_tour);
        _ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(_human != null)
                    _human.clickCol(e.getX()/64);
            }
        };
        this.addMouseListener(_ma);
    }
    public void setHumanListener(Human human) {
        _human = human;
    }
    private void save(ActionEvent evt) {
        Game.getGame().save();
    }
    public void actionPerformed(ActionEvent evt) {
        this.setVisible(false);
        GameWindow.getGameWindow().get_gm().setVisible(true);
    }
    public void setLabelText(String text){
        _tour.setText(text);
        this.repaint();
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
                //System.out.println("Vrai pour : " + i);
                int xa = i / 7;
                int ya = i % 7;
                g.setColor(Color.red);
                g.fillOval(xa * 64, (GridUtils.NbLine - ya - 1) * 64, 64, 64);
            }
        }
        long bitboard2 = _grid.getBitboardPlayer2();
        for(int i = 0; i < 48; i ++) {
            if((bitboard2>>i)%2 == 1) {
                //System.out.println("Vrai pour : " + i);
                int xa = i / 7;
                int ya = i % 7;
                g.setColor(Color.blue);
                g.fillOval(xa * 64, (GridUtils.NbLine - ya - 1) * 64, 64, 64);
            }
        }

    }

}