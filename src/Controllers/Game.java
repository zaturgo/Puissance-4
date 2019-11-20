package Controllers;


import Models.*;
import Utils.GridUtils;
import Views.GameGrid;
import Views.GameWindow;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
    }
    public Grid getGrid() {
        return _grid;
    }
    public void load() {
        try {
            Scanner scanner = new Scanner(new File("Partie1"));
            while (scanner.hasNextLine()) {
                String datas[] = scanner.nextLine().split(" ");
                _grid.setNbMoves(Integer.parseInt(datas[2]));
                _grid.setMask(Integer.parseInt(datas[1]));
                _grid.setBitboard(Integer.parseInt(datas[0]));
            }
            scanner.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void save() {
        try {
            PrintWriter writer = new PrintWriter("Partie1", "UTF-8");
            writer.print(_grid.getBitboard() + " " + _grid.getMask() + " " + _grid.getNbMoves());
            writer.close();
        }
        catch(Exception e) {
            System.out.println("Erreur d'écriture dans :" + "Partie1");
        }
    }
    public void start() throws CloneNotSupportedException {
        boolean game = true;
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(_P1);
        tabPlayers.add(_P2);
        //game loop, stops when game == false;
        while(game){
            for (int i = 0; i<tabPlayers.size(); i++) {
                System.out.println("Tour du joueur "+i);
                int playerMove = tabPlayers.get(i).getAction((Grid)_grid.clone());
                if(!_grid.canPlay(playerMove)) {
                    System.out.println("Play invalid !");
                    continue;
                }
                if (_grid.isWinningMove(playerMove)){
                    game = false;
                    System.out.println("Joueur gagnant:"+i);
                }
                _grid.play(playerMove);
                GameWindow.getGameWindow().update();
                //tabPlayers.get(i).saveOpeningBook("test" + i);
            }
       }
    }
}
