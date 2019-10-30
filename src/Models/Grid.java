package Models;

import Utils.GridUtils;
import com.sun.corba.se.spi.copyobject.CopyobjectDefaults;

import java.util.ArrayList;

public class Grid {
    ///
    /// The grid represents the game grid
    /// 0 = no token placed
    /// int > 0 = id of the player's token
    ///
    private ArrayList<Integer> _grid[] = new ArrayList[GridUtils.NbCol];

    ///
    /// Constructor
    ///
    public Grid() {
        // Init grid to 0
        for (int i = 0;i < _grid.length; i++) {
            _grid[i] = new ArrayList<>();
        }
    }
    ///
    /// Place the token in the grid
    /// If the token can't be placed, throw an InvalidArgumentException
    ///
    public void placeToken(int playerId, int col) {
        if(_grid[col].size() >= GridUtils.NbLine)
            throw new IllegalArgumentException();

        _grid[col].add(playerId);
    }
    ///
    /// Returns 0 if none wins
    /// Otherwise it returns the winner's playerId
    ///
    public int checkWin() {
        //return GridUtils.checkWin(_grid);
        return 0;
    }
    ///
    /// Returns the 2 dimensions tabs with the tokens
    ///
    public int[][] getTokens() {
        // Copy to avoid modifications out of the class
        //return GridUtils.copyGrid(_grid);
        return new int[][]{};
    }
    public ArrayList[] newGetTokens() {
        return _grid; // TODO: faire une copie ici
    }
}
