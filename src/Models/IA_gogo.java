package Models;

import Models.Player;
import Utils.GridUtils;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class IA_gogo extends Player {
    private int _otherPlayerId;
    private Random r = new Random();
    private int _lastPlayChoose = 2; // Starts to 2 so we'll place it in the middle if we start
    public IA_gogo(Integer _id) {
        super(_id);
        if(_id == 1) {//set opponent id
            _otherPlayerId = 2;
        }
        else {
            _otherPlayerId = 1;
        }
    }
    @Override
    public String toString() {
        return "Gogo";
    }
    @Override
    public int getAction(Grid grid) {
        try {
            sleep(1000);
        }
        catch(Exception e) {
        }
        OldGrid grid1 = new OldGrid();
        grid1.toArray(grid);
        // First, let's see if we can win
        int temp = GridUtils.getWinnableCol(grid1.getTokens(), _id);
        if(temp != -1) {
            System.out.println("winning");
            return temp;
        }
        // Then, let's see if the other player can win next turn
        temp = GridUtils.getWinnableCol(grid1.getTokens(), _otherPlayerId);
        if(temp != -1) {
            System.out.println("block");
            return temp;
        }
        // Then, let's see if we can create an unavoidable win next turn
        temp = GridUtils.getUnavoidableWinNextTurn(grid1.getTokens(), _id);
        if(temp != -1) {
            System.out.println("coup inevitable");
            return temp;
        }
        // Same for the other player
        temp = GridUtils.getUnavoidableWinNextTurn(grid1.getTokens(), _otherPlayerId);
        if(temp != -1) {
            System.out.println("block coup inevitable");
            return temp;
        }
        //check if two aligned to put a third
        temp = GridUtils.threeAligned(grid1.getTokens(),_id);
        if (temp!=-1&& GridUtils.opponentNotWinningNextTurn(grid1.getTokens(), temp, _id,_otherPlayerId)) {
            System.out.println("3aligned");
            return temp;
        }
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        int [][] tempTab = GridUtils.copyGrid(grid1.getTokens());
        for(int i = 0; i < 7; i++) {
            if(GridUtils.placeToken(_id, i, GridUtils.copyGrid(grid1.getTokens())) &&//if we can place and check opponent winning
                    GridUtils.getUnavoidableWinNextTurn(tempTab, _otherPlayerId) == -1 &&
                    GridUtils.getWinnableCol(tempTab, _otherPlayerId) == -1
            ) {
                possibleMoves.add(i);
            }
        }
        if(possibleMoves.size() > 0) {//if there is place without make the opponent winning
            System.out.println("random");
            return possibleMoves.get(r.nextInt(possibleMoves.size()));
        }
        return 1;
    }
}
