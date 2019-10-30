package Models;

public class IA_negamax extends Player {

    private TranspositionTable _transTable;

    public IA_negamax(Integer _id) {
        super(_id);
        _transTable = new TranspositionTable(8388593); //8388593 prime = 64MB of transposition table
    }

    @Override
    public int getAction(Grid grid) {
        try {
            return negamax(grid, -1, 1);// weak version
        }
        catch(Exception e)
        {
            return 0;
        }
    }
    private int negamax(Grid grid, int alpha, int beta) throws CloneNotSupportedException{
        if(grid.getNbMoves() == grid.width*grid.height) // check for draw game
            return 0;

        for(int x = 0; x < grid.width; x++) // check if current player can win next move
            if(grid.canPlay(x) && grid.isWinningMove(x))
                return (grid.width*grid.height+1 - grid.getNbMoves())/2;

        int max = (grid.width*grid.height-1 - grid.getNbMoves())/2;	// upper bound of our score as we cannot win immediately
        int val = _transTable.get(grid.getKey());
        if(val != 0)
            max = val + grid.MIN_SCORE - 1;

        if(beta > max) {
            beta = max;                     // there is no need to keep beta above our max possible score.
            if(alpha >= beta) return beta;  // prune the exploration if the [alpha;beta] window is empty.
        }

        for(int x = 0; x < grid.width; x++) // compute the score of all possible next move and keep the best one
            if(grid.canPlay(x)) {
                Grid grid2 = (Grid)grid.clone();
                grid2.play(x);               // It's opponent turn in P2 position after current player plays x column.
                int score = -negamax(grid2, -beta, -alpha); // explore opponent's score within [-beta;-alpha] windows:
                // no need to have good precision for score better than beta (opponent's score worse than -beta)
                // no need to check for score worse than alpha (opponent's score worse better than -alpha)

                if(score >= beta) return score;  // prune the exploration if we find a possible move better than what we were looking for.
                if(score > alpha) alpha = score; // reduce the [alpha;beta] window for next exploration, as we only
                // need to search for a position that is better than the best so far.
            }
        return alpha;
    }
}
