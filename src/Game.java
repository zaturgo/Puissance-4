import java.util.ArrayList;

public class Game {
    public void start(){
        boolean game = true;
        Human p1 = new Human("Jean", 1);
        IA p2 = new IA( 2);
        ArrayList<Player> tabPlayers= new ArrayList<>();
        tabPlayers.add(p1);
        tabPlayers.add(p2);
        Grid grid = new Grid();
        while(game){
            for (int i = 0; i<tabPlayers.size(); i++){
                System.out.println("Tour du premier joueur");
                grid.placeToken(p1.get_id(),p1.getAction());
                grid.checkWin();
            }
        }
    }
}
