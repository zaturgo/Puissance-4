package Views;

import Controllers.Game;
import Models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameMenu extends JPanel {
    private JComboBox _P1Select;
    private JComboBox _P2Select;
    private JButton _startBtn = new JButton("Start");
    private JButton _loadBtn = new JButton("Charger");

    private static Player P2 =new IA_gogo(2);;
    private static Player P1=new IA_gogo(1);;
    public GameMenu() {
        Object[] elements = new Object[]{"IAGogo", "IAnegamax", "Humain"};
        _P1Select = new JComboBox(elements);
        _P2Select = new JComboBox(elements);
        _P1Select.addActionListener(this::changePlayer);
        _P2Select.addActionListener(this::changePlayer);
        _startBtn.addActionListener(this::startClicked);
        _loadBtn.addActionListener(this::load);
        this.add(_P1Select);
        this.add(_P2Select);
        this.add(_startBtn);
        this.add(_loadBtn);
    }
    private void startClicked(ActionEvent evt) {
        this.setVisible(false);
        Game.getGame().reset();
        Game.getGame().setPlayers(P1, P2);
        GameWindow.getGameWindow().startGame();
    }
    private void load(ActionEvent evt) {
        Game.getGame().load();
        Game.getGame().setPlayers(P1, P2);
        GameWindow.getGameWindow().startGame();
    }
    private void changePlayer(ActionEvent evt) {
        switch(this._P1Select.getSelectedItem().toString()){
            case "IAGogo":
                P1 = new IA_gogo(1);
                break;
            case"IAnegamax":
                P1 = new IA_negamax(1);
                break;
            case"Humain":
                P1 = new Human("Pedro",1);
                break;
        }
        switch(this._P2Select.getSelectedItem().toString()){
            case "IAGogo":
                P2 = new IA_gogo(2);
                break;
            case"IAnegamax":
                P2 = new IA_negamax(2);
                break;
            case"Humain":
                P2 = new Human("Rico",2);
                break;
        }
    }
}
