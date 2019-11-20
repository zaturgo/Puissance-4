package Views;

import Controllers.Game;
import Models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameMenu extends JPanel {
    private JComboBox _P1Select;
    private JComboBox _P2Select;
    private JButton _startBtn = new JButton("Start");

    private static Player P2 =new IA_gogo(2);;
    private static Player P1=new IA_gogo(1);;
    private static GameWindow home;
    public GameMenu(GameWindow home) {
        Object[] elements = new Object[]{"IAGogo", "IAnegamax", "Humain"};
        _P1Select = new JComboBox(elements);
        _P2Select = new JComboBox(elements);
        _P1Select.addActionListener(this::actionPerformed);
        _P2Select.addActionListener(this::actionPerformed);
        _startBtn.addActionListener(this::actionPerformed);
        this.add(_P1Select);
        this.add(_P2Select);
        this.add(_startBtn);
        this.home = home;
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("Start")){
            this.setVisible(false);
            Game game = new Game(P1,P2, home);
            try {
                game.start();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }else{
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
        Game.getGame().setPlayers(P1, P2);
        home.startGame();
    }
}
