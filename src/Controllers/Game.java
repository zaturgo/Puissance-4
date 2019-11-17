package Controllers;


import Models.*;
import Views.GameGrid;
import Views.GameWindow;

import java.util.ArrayList;

public class Game {
    private Grid _grid;
    private GameWindow _gameWindow;
    private Player _P1;
    private Player _P2;

    public Game(Player P1, Player P2, GameWindow home) {
        _P1 = P1;
        _P2 = P2;
        _grid = new Grid();
        home.add(new GameGrid(_grid));
        _gameWindow = home;
        _gameWindow.update();

    }
    public void start(){
        boolean game = true;
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(_P1);
        tabPlayers.add(_P2);
        //game loop, stops when game == false;
        while(game){
            for (int i = 0; i<tabPlayers.size(); i++){
                System.out.println("Tour du joueur "+i);
                try {
                    //add token
                    _grid.placeToken(tabPlayers.get(i).get_id(), tabPlayers.get(i).getAction(_grid.getTokens()));
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
