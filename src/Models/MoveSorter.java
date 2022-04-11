package Models;

import Utils.GridUtils;

/**
 * This class helps sorting the next moves
 *
 * You have to add moves first with their score
 * then you can get them back in decreasing score
 *
 * This class implement an insertion sort that is in practice very
 * efficient for small number of move to sort (max is Position::WIDTH)
 * and also efficient if the move are pushed in approximatively increasing
 * order which can be acheived by using a simpler column ordering heuristic.
 */

public class MoveSorter {
    /*
     * Add a move in the container with its score.
     * You cannot add more than Position::WIDTH moves
     */
    void add(long move, int score)
    {
        int pos = size++;
        for(; pos != 0 && entries[pos-1].score > score; --pos)
            entries[pos] = entries[pos-1];

        entries[pos] = new entry();
        entries[pos].move = move;
        entries[pos].score = score;
    }

    /*
     * Get next move
     * @return next remaining move with max score and remove it from the container.
     * If no more move is available return 0
     */
    long getNext()
    {
        if(size != 0)
            return entries[--size].move;
        else
            return 0;
    }
    // number of stored moves
    int size;

    // Contains size moves with their score ordered by score
    private class entry{long move; int score;}
    private entry[] entries = new entry[GridUtils.NbCol];
}
