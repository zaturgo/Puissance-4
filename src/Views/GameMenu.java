package Views;

import Controllers.Game;
import Models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameMenu extends JPanel {
    private JButton _IAIA = new JButton("AI Versus");
    private JButton _IAPlayer= new JButton("Human VS AI");
    private JButton _PlayerPlayer= new JButton("Player Versus");
    private static GameWindow home;
    public GameMenu(GameWindow home) {
        _IAIA.addActionListener(this::actionPerformed);
        _IAPlayer.addActionListener(this::actionPerformed);
        _PlayerPlayer.addActionListener(this::actionPerformed);
        this.add(_IAIA);
        this.add(_IAPlayer);
        this.add(_PlayerPlayer);
        this.home = home;
    }
    public void actionPerformed(ActionEvent evt) {
        Player P1;
        Player P2;
        if (evt.getActionCommand().equals("AI Versus")){
            System.out.println("AI VS AI");
            P1 = new IA_gogo(1);
            P2 = new IA_gogo(2);
        } else if (evt.getActionCommand().equals("Human VS AI")){
            System.out.println("Human VS AI");
            P1 = new IA_gogo(1);
            P2 = new Human("toto", 2);
        } else {
            System.out.println("Human VS Human");
            P1 = new Human("tutu", 1);
            P2 = new Human("toto", 2);
        }
        Game.getGame().setPlayers(P1, P2);
        home.startGame();
    }
}
