package Controllers;


import Models.Grid;
import Models.Human;
import Models.IA_gogo;
import Models.Player;
import Views.GameWindow;

import java.util.ArrayList;

public class Game {
    private Grid _grid;
    private GameWindow _gameWindow;

    public Game() {
        _grid = new Grid();
        _gameWindow = new GameWindow(_grid);
    }
    public void start(){
        boolean game = true;
        Human p1 = new Human("Jean", 1);
        IA_gogo p2 = new IA_gogo( 2);
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(p1);
        tabPlayers.add(p2);
        //game loop, stops when game == false;
        while(game){
            for (int i = 0; i<tabPlayers.size(); i++){
                System.out.println("Tour du joueur "+i);
                try {
                    //add token
                    _grid.placeToken(tabPlayers.get(i).get_id(), tabPlayers.get(i).getAction());
                } catch(Exception e) {
                    System.out.println("Placement invalide");
                }
                _gameWindow.update();
                //check if 4 aligned
                int result = _grid.checkWin();
                if (result != 0){
                    game = false;
                    System.out.println("Joueur gagnant:"+result);
                    break;
                }
            }
        }
    }
}
