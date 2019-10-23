package Models;

import java.util.Scanner;

public abstract class Player {
    protected Integer _id;
    public abstract int getAction(int[][] tokens);

    public Player(Integer _id) {
        this._id = _id;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
}
