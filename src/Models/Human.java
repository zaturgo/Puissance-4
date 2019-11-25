package Models;

import Views.GameGrid;

public class Human extends Player {
    public Human(Integer _id) {
        super(_id);
    }
    @Override
    public String toString() {
        return "Joueur";
    }
    @Override
    public int getAction(Grid grid) {//get click from frame
        GameGrid.lastClickedCol = -1;
        while(GameGrid.lastClickedCol <= -1 || GameGrid.lastClickedCol >= 7) {
            try {
                Thread.sleep(100);
            }
            catch(Exception e) {
            }
        }
        return GameGrid.lastClickedCol;
    }
}
