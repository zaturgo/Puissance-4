package Views;

import Controllers.Game;
import Models.Grid;
import Models.Player;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private GameMenu _gm;
    private static GameWindow _instance;
    public static GameWindow getGameWindow() {
        if(_instance == null)
            _instance = new GameWindow();
        return _instance;
    }
    private GameWindow() {
        this.setTitle("Puissance 4");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        _gm = new GameMenu(this);
        this.add(_gm);
    }
    public void update() {
        this.invalidate();
        this.validate();
        this.repaint();
    }
    public void startGame() {
        this.add(new GameGrid());
        _gm.setVisible(false);
        _gm.repaint();
        this.update();
        ///
        /// On lance la partie dans un thread à part pour ne pas bloquer la fenetre
        ///
        Thread t = new Thread() {
            public void run() {
                try {
                    Game.getGame().start();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
