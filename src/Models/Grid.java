package Models;

import Utils.GridUtils;

public class Grid {
    ///
    /// The grid represents the game grid
    /// 0 = no token placed
    /// int > 0 = id of the player's token
    ///
    private int[][] _grid = new int[GridUtils.NbLine][GridUtils.NbCol];

    ///
    /// Constructor
    ///
    public Grid() {
        // Init grid to 0
        for (int i = 0;i < _grid.length; i++) {
            for (int j = 0;j < _grid[i].length;j++) {
                _grid[i][j] = 0;
            }
        }
    }
    ///
    /// Place the token in the grid
    /// If the token can't be placed, throw an InvalidArgumentException
    ///
    public void placeToken(int playerId, int col) {
        boolean placed = false;
        for(int i = _grid.length-1; i >= 0; --i) {
            if(_grid[i][col] == 0) {
                _grid[i][col] = playerId;
                placed = true;
                break;
            }
        }
        if(!placed)
            throw new IllegalArgumentException();
    }
    ///
    /// Returns 0 if none wins
    /// Otherwise it returns the winner's playerId
    ///
    public int checkWin() {
        return GridUtils.checkWin(_grid);
    }
    ///
    /// Returns the 2 dimensions tabs with the tokens
    ///
    public int[][] getTokens() {
        // Copy to avoid modifications out of the class
        return GridUtils.copyGrid(_grid);
    }
}
