package Models;

public class Grid implements Cloneable{
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    ///
    ///
    ///
    private long bitboard = 0;
    private long mask = 0;
    public final int height = 6;
    public final int width = 7;
    public final int MIN_SCORE = -(width*height)/2 + 3;
    public final int MAX_SCORE = (width*height+1)/2 - 3;

    private int moves = 0;

    public boolean canPlay(int col) {
        return (mask & top_mask(col)) == 0;
    }

    /**
     * Plays a possible move given by its bitmap representation
     *
     * param move: a possible move given by its bitmap representation
     *        only one bit of the bitmap should be set to 1
     *        the move should be a valid possible move for the current player
     */
    public void play(long move)
    {
        bitboard ^= mask;
        mask |= move;
        moves++;
    }
    public void play(int col)
    {
        play((mask + bottom_mask(col)) & column_mask(col));
    }
    // return a bitmask containg a single 1 corresponding to the bottom cell of a given column
    private long bottom_mask(int col) {
        return (long)1 << col*(height+1);
    }

    // return a bitmask containg a single 1 corresponding to the top cel of a given column
    private long top_mask(int col) {
        return (long)1 << height - 1 << col*(height+1);
    }

    // return a bitmask 1 on all the cells of a given column
    public long column_mask(int col) {
        return (((long)1 << height)-1) << col*(height+1);
    }

    long key()
    {
        return bitboard + mask;
    }

    public boolean isWinningMove(int col)
    {
        long pos = bitboard;
        pos |= (mask + bottom_mask(col)) & column_mask(col);
        return alignment(pos);
    }

    public boolean alignment(long pos ) {
        // horizontal
        long m = pos & (pos >> (height+1));
        if((m & (m >> (2*(height+1)))) > 0) return true;

        // diagonal 1
        m = pos & (pos >> height);
        if((m & (m >> (2*height))) > 0) return true;

        // diagonal 2
        m = pos & (pos >> (height+2));
        if((m & (m >> (2*(height+2)))) > 0) return true;

        // vertical;
        m = pos & (pos >> 1);
        if((m & (m >> 2)) > 0) return true;

        return false;
    }

    public int getNbMoves() {
        return moves;
    }

    ///
    /// return a compact representation of a position on WIDTH*(HEIGHT+1) bits.
    ///
    long getKey()
    {
        return bitboard + mask;
    }

    public void changePlayerTurn() {
        bitboard ^= mask;
        moves++;
    }

    /**
     * Score a possible move.
     * @param move, a possible move given in a bitmap format.
     * The score we are using is the number of winning spots
     * the current player has after playing the move.
     */
    public int moveScore(long move) {
        return popcount(compute_winning_position(bitboard | move, mask));
    }

    /**
     * counts number of bit set to one in a 64bits integer
     */
    public int popcount(long m) {
        int c = 0;
        for (c = 0; m != 0; c++) m &= m - 1;
        return c;
    }

    /**
     * Return a bitmap of all the possible next moves the do not lose in one turn.
     * A losing move is a move leaving the possibility for the opponent to win directly.
     *
     * Warning this function is intended to test position where you cannot win in one turn
     * If you have a winning move, this function can miss it and prefer to prevent the opponent
     * to make an alignment.
     */
    public long possibleNonLosingMoves() {
        long possible_mask = possible();
        long opponent_win = opponent_winning_position();
        long forced_moves = possible_mask & opponent_win;
        if(forced_moves != 0) {
            if((forced_moves & (forced_moves - 1))!=0) // check if there is more than one forced move
                return 0;                           // the opponnent has two winning moves and you cannot stop him
            else possible_mask = forced_moves;    // enforce to play the single forced move
        }
        return possible_mask & ~(opponent_win >> 1);  // avoid to play below an opponent winning spot
    }

    private long compute_winning_position(long position, long mask) {
        // vertical;
        long r = (position << 1) & (position << 2) & (position << 3);

        //horizontal
        long p = (position << (height+1)) & (position << 2*(height+1));
        r |= p & (position << 3*(height+1));
        r |= p & (position >> (height+1));
        p = (position >> (height+1)) & (position >> 2*(height+1));
        r |= p & (position << (height+1));
        r |= p & (position >> 3*(height+1));

        //diagonal 1
        p = (position << height) & (position << 2*height);
        r |= p & (position << 3*height);
        r |= p & (position >> height);
        p = (position >> height) & (position >> 2*height);
        r |= p & (position << height);
        r |= p & (position >> 3*height);

        //diagonal 2
        p = (position << (height+2)) & (position << 2*(height+2));
        r |= p & (position << 3*(height+2));
        r |= p & (position >> (height+2));
        p = (position >> (height+2)) & (position >> 2*(height+2));
        r |= p & (position << (height+2));
        r |= p & (position >> 3*(height+2));

        return r & (board_mask ^ mask);
    }

    /*
     * Return a bitmask of the possible winning positions for the opponent
     */
    private long opponent_winning_position() {
        return compute_winning_position(bitboard ^ mask, mask);
    }

    private long bottom(int width, int height) {
        return (width == 0) ? 0 : (bottom(width - 1, height) | (1L << ((width - 1) * (height + 1))));
    }

    private final long bottom_maskV = bottom(width, height);
    private final long board_mask = bottom_maskV * ((1L << height)-1);

    private long possible() {
        return (mask + bottom_maskV) & board_mask;
    }
    // Returns bitboard of player 1
    public long getBitboardPlayer1() {
        if(moves%2 == 1)
            return bitboard;
        else
            return bitboard ^ mask;
    }
    // Retruns bitboard of player 2
    public long getBitboardPlayer2() {
        if(moves%2 == 1)
            return bitboard ^ mask;
        else
            return bitboard;
    }
    public void debugGrid() {
        System.out.println("bitboard p1: ");
        System.out.println(Long.toBinaryString(getBitboardPlayer1()));
        System.out.println("bitboard p2: ");
        System.out.println(Long.toBinaryString(getBitboardPlayer2()));
        System.out.println("mask :");
        System.out.println(Long.toBinaryString(mask));
    }
}
