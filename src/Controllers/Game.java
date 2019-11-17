package Controllers;


import Models.*;
import Utils.GridUtils;
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
    public void start() throws CloneNotSupportedException {
        boolean game = true;
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(_P1);
        tabPlayers.add(_P2);
        //game loop, stops when game == false;
       while(game){
//           for (int i = 0; i<_grid.toArray().length; i++){
//                for (int j = 0; j<_grid.toArray()[i].length; j++){
//                    System.out.print(_grid.toArray()[i][j]);
//                }
//               System.out.println("");
//           }
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
