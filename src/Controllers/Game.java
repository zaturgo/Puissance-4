package Controllers;


import Models.*;
import Utils.GridUtils;
import Views.GameGrid;
import Views.GameWindow;

import java.util.ArrayList;

public class Game {
    private Grid _grid;
    private Player _P1;
    private Player _P2;
    private static Game _instance;
    static public Game getGame() {
        if(_instance == null)
            _instance = new Game();
        return _instance;
    }

    private Game() {
        _grid = new Grid();
    }
    public void setPlayers(Player P1, Player P2) {
        _P1 = P1;
        _P2 = P2;
        _grid = new Grid();
    }
    public Grid getGrid() {
        return _grid;
    }
    public void start() throws CloneNotSupportedException {
        boolean game = true;
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(_P1);
        tabPlayers.add(_P2);
        int g = 0;
        int w=0;
        //game loop, stops when game == false;
        while(game){
            for (int i = 0; i<tabPlayers.size() && game; i++){
                GameWindow.getGameWindow().get_gg().setLabelText("Tour du joueur "+(i+1));
                int playerMove = tabPlayers.get(i).getAction((Grid)_grid.clone());
                if(!_grid.canPlay(playerMove)) {
                    System.out.println("Play invalid !");
                    i--;
                    continue;
                }
                if (_grid.isWinningMove(playerMove)){
                    game = false;
                    GameWindow.getGameWindow().get_gg().setLabelText("Joueur gagnant:"+(w+1));
                }
                _grid.play(playerMove);

                GameWindow.getGameWindow().update();
                //tabPlayers.get(i).saveOpeningBook("test" + i);
                if (_grid.getNbMoves()==42){
                    game = false;
                    GameWindow.getGameWindow().get_gg().setLabelText("Egalité");
                }
            }

        }
    }
}
