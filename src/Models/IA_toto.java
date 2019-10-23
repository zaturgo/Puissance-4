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
    public int getAction(int[][] tokens) {
        // First, let's see if we can win
        int temp = GridUtils.getWinnableCol(tokens, _id);
        if(temp != -1)
            return temp;
        // Then, let's see if the other player can win next turn
        temp = GridUtils.getWinnableCol(tokens, _otherPlayerId);
        if(temp != -1)
            return temp;
        // Then, let's see if we can create an unavoidable win next turn
        temp = GridUtils.getUnavoidableWinNextTurn(tokens, _id);
        if(temp != -1)
            return temp;
        // Same for the other player
        temp = GridUtils.getUnavoidableWinNextTurn(tokens, _otherPlayerId);
        if(temp != -1)
            return temp;
        // Return the first col free
        for (int i = 0; i < tokens.length; i++) {
            if(GridUtils.placeToken(_id, i, tokens)) {
                return i;
            }
        }
        return -1;
    }
}
