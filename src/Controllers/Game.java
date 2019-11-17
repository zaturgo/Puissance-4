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
    public void start() throws CloneNotSupportedException {
        boolean game = true;
        ArrayList<Player> tabPlayers= new ArrayList<>();

        tabPlayers.add(new IA_negamax(1));
        tabPlayers.add(new Human( "pedro", 2));
        //IA_negamax test = new IA_negamax(2);
        //game loop, stops when game == false;
       while(game){
            for (int i = 0; i<tabPlayers.size(); i++){
                System.out.println("Tour du joueur "+i);
                int playerMove = tabPlayers.get(i).getAction((Grid)_grid.clone());
                if(!_grid.canPlay(playerMove)) {
                    System.out.println("Play invalid !");
                    continue;
                }
                if (_grid.isWinningMove(playerMove)){
                    //game = false;
                    System.out.println("Joueur gagnant:"+i);
                    //break;
                }
                _grid.play(playerMove);
                _grid.toArray();
                _gameWindow.update();
                //tabPlayers.get(i).saveOpeningBook("test" + i);
            }
       }
    }
}
