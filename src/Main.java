import Controllers.Game;

public class Main {
    public static void main(String[] args)
    {
        try {
            Game game = new Game();
            game.start();
        }
        catch(Exception e )
        {
            System.out.println("Pas de chance"); //todo
        }
    }
}
