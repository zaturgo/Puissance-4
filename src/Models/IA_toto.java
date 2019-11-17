package Models;

import Utils.GridUtils;

public class IA_toto extends Player {
    private int _otherPlayerId;
    private int _lastPlayChoose = 2; // Starts to 2 so we'll place it in the middle if we start

    public IA_toto(Integer _id) {
        super(_id);
        if(_id == 1)
            _otherPlayerId = 2;
        else
            _otherPlayerId = 1;
    }

    @Override
    public int getAction(Grid grid) {
        // First, let's see if we can win
        for (int i = 0; i < grid.width; i++) {
            if(grid.canPlay(i))
                if(grid.isWinningMove(i))
                    return i;
        }
        grid.changePlayerTurn();
        // Then, let's see if the other player can win next turn
        for (int i = 0; i < grid.width; i++) {
            if(grid.canPlay(i))
                if(grid.isWinningMove(i))
                    return i;
        }
        // Return the first col free
        for (int i = 0; i < grid.width; i++) {
            if(grid.canPlay(i)) {
                return i;
            }
        }
        return -1;
    }
    public int getNbWinningMove(Grid grid) {
        int count = 0;
        for(int i = 0; i < grid.width; i ++) {
            if(grid.canPlay(i))
                if(grid.isWinningMove(i))
                    count++;
        }
        return count;
    }
}
