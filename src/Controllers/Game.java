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
    //game loop, stops when game == false;
        while(game){
            for (int i = 0; i<tabPlayers.size(); i++){
                System.out.println("Tour du joueur "+i);
                try{
                    //add token
                    _grid.placeToken(tabPlayers.get(i).get_id(),tabPlayers.get(i).getAction());
                }catch(Exception e){
                    System.out.println("Placement invalide");
                }
                //check if 4 aligned
                int result = _grid.checkWin();
                if (result!=0){
                    game= true;
                    System.out.println("Joueur gagnant:"+result);
                }
                _grid.debugGrid();
            }
        }
    }
}
