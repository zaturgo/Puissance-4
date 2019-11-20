package Models;

import Views.GameGrid;

public class Human extends Player {
    private String _name;

    public Human(String _name, Integer _id) {
        super(_id);
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }

    @Override
    public int getAction(Grid grid) {
        GameGrid.lastClickedCol = -1;
        while(GameGrid.lastClickedCol == -1 || GameGrid.lastClickedCol >= 7) {
            try {
                Thread.sleep(100);
            }
            catch(Exception e) {

            }
        }
        return GameGrid.lastClickedCol;
    }
}
