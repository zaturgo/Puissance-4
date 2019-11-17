import Controllers.Game;
import Models.Human;
import Models.IA_gogo;
import Models.IA_toto;
import Models.Player;
import Views.GameMenu;
import Views.GameWindow;

public class Main {


    public static void main(String[] args)
    {
        GameWindow home = new GameWindow();
        GameMenu gm = new GameMenu(home);
        home.add(gm);
    }
}
