package Controllers;


import Models.*;
import Utils.GridUtils;
import Views.GameGrid;
import Views.GameWindow;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends Thread {
    private Grid _grid;
    private Player _P1;
    private Player _P2;
    private volatile boolean _stopGame = false;
    public void stopGame() {
        _stopGame = true;
    }
    public Game() {
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
            while (scanner.hasNextLine()) {//reads line per line
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

    @Override
    public void run() {
        boolean game = true;
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(_P1);
        tabPlayers.add(_P2);

        //game loop, stops when game == false;
        System.out.println("NEW GAME");
        while(game && !_stopGame){//while game still running without draw game or winning
            GameWindow.getGameWindow().get_gg().setLabelText("Tour du joueur "+((_grid.getNbMoves()%2)+1));//change the label on the right
            int playerMove = -1;
            try {
                playerMove = tabPlayers.get(_grid.getNbMoves()%2).getAction((Grid)_grid.clone());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            if(!_grid.canPlay(playerMove)) {//if move outside the grid
                System.out.println("Coup invalide !");
                continue;
            }
            if (_grid.isWinningMove(playerMove)){//if move makes a win
                game = false;
                GameWindow.getGameWindow().get_gg().setLabelText("Joueur gagnant:"+((_grid.getNbMoves()%2)+1));
            }
            _grid.play(playerMove);//play the move
            if (_grid.getNbMoves()==42){//if draw stop the game and change label
                game = false;
                GameWindow.getGameWindow().get_gg().setLabelText("Egalité");
            }
        }
    }
}
