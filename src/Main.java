import Controllers.Game;
import Models.Human;
import Models.IA_gogo;
import Models.IA_toto;
import Models.Player;
import Views.GameMenu;
import Views.GameWindow;

import java.awt.*;

public class Main {


    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow.getGameWindow();
            }
        });
    }
}
