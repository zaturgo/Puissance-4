package Models;

import Models.Player;
import Utils.GridUtils;

import java.util.Random;

public class IA_gogo extends Player {
    private int _otherPlayerId;
    private int _lastPlayChoose = 2; // Starts to 2 so we'll place it in the middle if we start
    public IA_gogo(Integer _id) {
        super(_id);
        if(_id == 1)
            _otherPlayerId = 2;
        else
            _otherPlayerId = 1;
    }
@Override
    public int getAction(Grid grid) {
        // First, let's see if we can win
        int temp = GridUtils.getWinnableCol(grid.toArray(), _id);
        if(temp != -1)
            return temp;
        // Then, let's see if the other player can win next turn
        temp = GridUtils.getWinnableCol(grid.toArray(), _otherPlayerId);
        if(temp != -1)
            return temp;
        // Then, let's see if we can create an unavoidable win next turn
        temp = GridUtils.getUnavoidableWinNextTurn(grid.toArray(), _id);
        if(temp != -1)
            return temp;
        // Same for the other player
        temp = GridUtils.getUnavoidableWinNextTurn(grid.toArray(), _otherPlayerId);
        if(temp != -1)
            return temp;


        //check if two aligned to put a third
        temp = GridUtils.threeAligned(grid.toArray(),_id);
        if (temp!=-1){
            return temp;
        }
        //else random
        Random r = new Random();
        int col = r.nextInt(6);
            if(GridUtils.placeToken(_id, col, grid.toArray())) {
                return col;
            }
        return -1;
    }

}
