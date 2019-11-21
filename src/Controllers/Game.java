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
    public void reset() {
        _grid = new Grid();
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
                _grid.setMask(Long.parseLong(datas[1]));
                _grid.setBitboard(Long.parseLong(datas[0]));
                System.out.println(datas[2]);
                System.out.println(datas[1]);
                System.out.println(datas[0]);
            }
            GameWindow.getGameWindow().update();
            scanner.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void save() {
        try {
            File Fileright = new File("Partie1");
            PrintWriter writer = new PrintWriter(Fileright);
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
        System.out.println("NEW GAME");
        while(game){
            System.out.println("=====");
            System.out.println(_grid.getNbMoves()%2);
                GameWindow.getGameWindow().get_gg().setLabelText("Tour du joueur "+((_grid.getNbMoves()%2)+1));
                int playerMove = tabPlayers.get(_grid.getNbMoves()%2).getAction((Grid)_grid.clone());
                if(!_grid.canPlay(playerMove)) {
                    System.out.println("Play invalid !");
                    continue;
                }
                if (_grid.isWinningMove(playerMove)){
                    System.out.println("Partie gagnée");
                    game = false;
                    System.out.println(game);
                    GameWindow.getGameWindow().get_gg().setLabelText("Joueur gagnant:"+((_grid.getNbMoves()%2)+1));
                }
                _grid.play(playerMove);

//                GameWindow.getGameWindow().update();
                //tabPlayers.get(i).saveOpeningBook("test" + i);
                if (_grid.getNbMoves()==42){
                    System.out.println("Egalité");
                    game = false;
                    GameWindow.getGameWindow().get_gg().setLabelText("Egalité");
                }
                System.out.println(game);

        }
    }
}
