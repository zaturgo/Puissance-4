package Views;

import Controllers.Game;
import Models.Grid;
import Models.Player;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private GameMenu _gm;
    private GameGrid _gg;
    private static GameWindow _instance;
    private Game _game;

    public static GameWindow getGameWindow() {
        if(_instance == null)
            _instance = new GameWindow();
        return _instance;
    }
    private GameWindow() {
        this.setTitle("Puissance 4");
        this.setSize(1000, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        _gm = new GameMenu();
        this.add(_gm);
    }
    public void update() {
        this.invalidate();
        this.validate();
        this.repaint();
    }

    public void startMenu() {
        if(_game != null)
            _game.stopGame();
        this.remove(_gg);
        _gm.setVisible(true);
        update();
    }
    public GameGrid get_gg() {
        return _gg;
    }
    public void loadGame(Player P1, Player P2) {
        _game = new Game();
        _game.load();
        _game.setPlayers(P1, P2);

        _gm.setVisible(false);
        _gg = new GameGrid(_game.getGrid());
        this.add(_gg);

        _game.start();
    }
    public void startGame(Player P1, Player P2) {
        _game = new Game();
        _game.setPlayers(P1, P2);

        _gm.setVisible(false);
        _gg = new GameGrid(_game.getGrid());
        this.add(_gg);
        _game.start();
    }
}
