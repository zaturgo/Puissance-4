package Models;

public class IA_negamax extends Player {

    private TranspositionTable _transTable;
    private int[] columnOrder = {3, 4, 2, 5, 1, 6, 0};// initialize the column exploration order, starting with center columns
    private long nodecount = 0;

    public IA_negamax(Integer _id) {
        super(_id);
        _transTable = new TranspositionTable(8388593); //8388593 prime = 64MB of transposition table
    }

    @Override
    public int getAction(Grid grid) {
        for (int i = 0; i < 7; i ++) {
            if(grid.isWinningMove(columnOrder[i]))
                return columnOrder[i];
        }
        try {
            int min = 1000;
            int minMove = 0;
            for (int i = 0; i < 7; i ++) {
                long debut = System.currentTimeMillis();
                Grid tempGrid = (Grid)grid.clone();
                if(tempGrid.canPlay(columnOrder[i])) {
                    tempGrid.play(columnOrder[i]);
                    int temp = solve(tempGrid, true);
                    if(System.currentTimeMillis()-debut > 30000) { // If time is higher than 30s, we store this position

                    }
                    //System.out.println(columnOrder[i] + " : " + temp);
                    if (temp < min) {
                        min = temp;
                        minMove = columnOrder[i];
                    }
                }
            }
            return minMove;// weak version
        }
        catch(Exception e)
        {
            return 0;
        }
    }
    public int solve(Grid grid, boolean weak) throws CloneNotSupportedException
    {
        nodecount = 0;
        //System.out.println(nodecount);
        _transTable = new TranspositionTable(8388593);
        int min = -(grid.width*grid.height - grid.getNbMoves())/2;
        int max = (grid.width*grid.height+1 - grid.getNbMoves())/2;
        if(weak) {
            min = -1;
            max = 1;
        }

        while(min < max) {                    // iteratively narrow the min-max exploration window
            int med = min + (max - min)/2;
            if(med <= 0 && min/2 < med) med = min/2;
            else if(med >= 0 && max/2 > med) med = max/2;
            int r = negamax(grid, med, med + 1);   // use a null depth window to know if the actual score is greater or smaller than med
            if(r <= med) max = r;
            else min = r;
        }
        return min;
    }
    public int negamax(Grid grid, int alpha, int beta) throws CloneNotSupportedException {
        nodecount++; // increment counter of explored nodes

        long next = grid.possibleNonLosingMoves();
        //System.out.println(next);
        if(next == 0)     // if no possible non losing move, opponent wins next move
            return -((grid.width*grid.height) - grid.getNbMoves())/2;

        if(grid.getNbMoves() >= (grid.width*grid.height) - 2) // check for draw game
            return 0;

        int min = -((grid.width*grid.height)-2 - grid.getNbMoves())/2;	// lower bound of score as opponent cannot win next move
        if(alpha < min) {
            alpha = min;                     // there is no need to keep beta above our max possible score.
            if(alpha >= beta) return alpha;  // prune the exploration if the [alpha;beta] window is empty.
        }

        int max = ((grid.width*grid.height)-1 - grid.getNbMoves())/2;	// upper bound of our score as we cannot win immediately
        int val = _transTable.get(grid.key());
        if(val != 0)
            max = val + grid.MIN_SCORE - 1;

        if(beta > max) {
            beta = max;                     // there is no need to keep beta above our max possible score.
            if(alpha >= beta) return beta;  // prune the exploration if the [alpha;beta] window is empty.
        }


        MoveSorter moves = new MoveSorter();

        for(int i = grid.width -1; i>=0; i--) {
            long move = next & grid.column_mask(columnOrder[i]);
            if (move != 0)
               moves.add(move, grid.moveScore(move));
        }
        long next2 = moves.getNext();
        while(next2 != 0) {
            Grid grid2 = (Grid)grid.clone();
            grid2.play(next2);  // It's opponent turn in P2 position after current player plays x column.
            int score = -negamax(grid2, -beta, -alpha); // explore opponent's score within [-beta;-alpha] windows:
            // no need to have good precision for score better than beta (opponent's score worse than -beta)
            // no need to check for score worse than alpha (opponent's score worse better than -alpha)

            if(score >= beta) return score;  // prune the exploration if we find a possible move better than what we were looking for.
            if(score > alpha) alpha = score; // reduce the [alpha;beta] window for next exploration, as we only
            // need to search for a position that is better than the best so far.
            next2 = moves.getNext();
        }

        _transTable.put(grid.key(), (short)(alpha - grid.MIN_SCORE + 1)); // save the upper bound of the position
        return alpha;
    }
}
