package Models;

import Models.Player;

public class IA_gogo extends Player {
    public IA_gogo(Integer _id) {
        super(_id);
    }

    @Override
    public int getAction(int[][] tokens) {
        return 0;
    }
}
