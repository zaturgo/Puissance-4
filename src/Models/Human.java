package Models;

import Views.GameGrid;
import Views.GameWindow;

public class Human extends Player {
    private String _name;
    private int _clickedCol = -1;

    public Human(String _name, Integer _id) {
        super(_id);
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }

    public void clickCol(int col) {
        _clickedCol = col;
    }

    @Override
    public int getAction(Grid grid) {
        GameWindow.getGameWindow().get_gg().setHumanListener(this);
        _clickedCol = -1;
        while(_clickedCol <= -1 || _clickedCol >= 7) {
            try {
                Thread.sleep(100);
            }
            catch(Exception e) {

            }
        }
        GameWindow.getGameWindow().get_gg().setHumanListener(null);
        return _clickedCol;
    }
}
