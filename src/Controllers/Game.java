package Controllers;


import Models.*;
import Utils.GridUtils;
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
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(new Human("Jean", 1));
        tabPlayers.add(new Human("Jean2", 2));
        //game loop, stops when game == false;
        while(game){
            for (int i = 0; i<tabPlayers.size(); i++){
                System.out.println("Tour du joueur "+i);
                int playerMove = tabPlayers.get(i).getAction(_grid.getTokens());

                if (GridUtils.isWinningMove(playerMove, _grid, tabPlayers.get(i).get_id())){
                    game = false;
                    System.out.println("Joueur gagnant:"+tabPlayers.get(i).get_id());
                }

                try {
                    //add token
                    _grid.placeToken(tabPlayers.get(i).get_id(), playerMove);
                } catch(Exception e) {
                    System.out.println("Placement invalide en : " + playerMove);
                }

                _gameWindow.update();
            }
        }
    }
}
