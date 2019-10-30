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

    public void play(int col)
    {
        changePlayerTurn();
        mask |= mask + bottom_mask(col);
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
    private long column_mask(int col) {
        return (((long)1 << height)-1) << col*(height+1);
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
