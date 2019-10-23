package Controllers;

import java.util.ArrayList;

import Models.Grid;
import Models.Human;
import Models.IA;
import Models.Player;

public class Game {
    private Grid _grid;

    public Game() {
        _grid = new Grid();
    }

    public void start(){
        boolean game = true;
        Human p1 = new Human("Jean", 1);
        IA p2 = new IA( 2);
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(p1);
        tabPlayers.add(p2);

        while(game){
            for (int i = 0; i<tabPlayers.size(); i++){
                System.out.println("Tour du premier joueur");
                _grid.placeToken(p1.get_id(),p1.getAction());
                _grid.checkWin();
            }
        }
    }
}
