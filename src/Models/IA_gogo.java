package Models;

import Models.Player;
import Utils.GridUtils;

import java.util.ArrayList;
import java.util.Random;

public class IA_gogo extends Player {
    private int _otherPlayerId;
    private Random r = new Random();
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
        OldGrid grid1 = new OldGrid();
        grid1.toArray(grid);
        // First, let's see if we can win
        int temp = GridUtils.getWinnableCol(grid1.getTokens(), _id);
        if(temp != -1)
            return temp;
        // Then, let's see if the other player can win next turn
        temp = GridUtils.getWinnableCol(grid1.getTokens(), _otherPlayerId);
        if(temp != -1)
            return temp;
        // Then, let's see if we can create an unavoidable win next turn
        temp = GridUtils.getUnavoidableWinNextTurn(grid1.getTokens(), _id);
        if(temp != -1)
            return temp;
        // Same for the other player
        temp = GridUtils.getUnavoidableWinNextTurn(grid1.getTokens(), _otherPlayerId);
        if(temp != -1)
            return temp;


        //check if two aligned to put a third
        temp = GridUtils.threeAligned(grid1.getTokens(),_id);
        if (temp!=-1&& GridUtils.opponentNotWinningNextTurn(grid1.getTokens(), temp, _id,_otherPlayerId)) {
            return temp;
        }
    ArrayList<Integer> possibleMoves = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            if(GridUtils.placeToken(_id, i, grid1.getTokens().clone()) &&
                    GridUtils.getUnavoidableWinNextTurn(grid1.getTokens(), _otherPlayerId) != -1 &&
                    GridUtils.getWinnableCol(grid1.getTokens(), _otherPlayerId) != -1
            ) {
                possibleMoves.add(i);
            }
        }
        if(possibleMoves.size() > 0)
            return possibleMoves.get(r.nextInt(possibleMoves.size()));
        return 1;

    }
    public int random(OldGrid grid1){
        int col =  r.nextInt(7);
        if(GridUtils.placeToken(_id, col, grid1.getTokens())) {
            return col;
        }else{
            return random(grid1);
        }
    }

}
