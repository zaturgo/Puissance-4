package Views;

import Models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameMenu extends JPanel {
    private JComboBox _P1Select;
    private JComboBox _P2Select;
    private JButton _startBtn = new JButton("Start");
    private JButton _loadBtn = new JButton("Charger");//init components

    public GameMenu() {
        Object[] elements = new Object[]{new IA_gogo(1), new IA_negamax(1), new Human(2)};
        _P1Select = new JComboBox(elements);
        elements = new Object[]{new IA_gogo(2), new IA_negamax(2), new Human(2)};//list of elements for the select
        _P2Select = new JComboBox(elements);

        _startBtn.addActionListener(this::startClicked);
        _loadBtn.addActionListener(this::load);
        this.add(_P1Select);
        this.add(_P2Select);
        this.add(_startBtn);
        this.add(_loadBtn);
    }
    private void startClicked(ActionEvent evt) {//when u start a game
        GameWindow.getGameWindow().startGame((Player)_P1Select.getSelectedItem(), (Player)_P2Select.getSelectedItem());
    }
    private void load(ActionEvent evt) {//when u want to load a game
        GameWindow.getGameWindow().loadGame((Player)_P1Select.getSelectedItem(), (Player)_P2Select.getSelectedItem());
    }
}
