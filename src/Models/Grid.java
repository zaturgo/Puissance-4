package Models;

public class Grid {
    ///
    /// The grid represents the game grid
    /// 0 = no token placed
    /// int > 0 = id of the player's token
    ///
    private int[][] _grid = new int[7][7];

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
    /// Show the grid in the console for debug (before vue)
    ///
    public void debugGrid() {
        // Init grid to 0
        for (int i = 0;i < _grid.length; i++) {
            for (int j = 0;j < _grid[i].length;j++) {
                System.out.print(_grid[i][j]);
            }
            System.out.println();
        }
    }
    ///
    /// Returns 0 if none wins
    /// Otherwise it returns the winner's playerId
    ///
    public int checkWin() {
        for (int i = 0;i < _grid.length; i++) {
            for (int j = 0;j < _grid[i].length;j++) {
                if(_grid[i][j] != 0) {
                    if (fourAligned(i, j))
                        return _grid[i][j];
                }
            }
        }
        return 0;
    }
    ///
    /// Look if 3 tokens are aligned next to the one placed in [line][col]
    ///
    private boolean fourAligned(int line, int col) {
        for(int count = 0; count < 4; count++) {
            if (line + count >= 7)
                break;
            if (_grid[line + count][col] != _grid[line][col]) {
                break;
            }
            if (count == 3)
                return true;
        }
        for(int count = 0; count < 4; count++) {
            if(col + count >= 7)
                break;
            if(_grid[line][col+count] != _grid[line][col]) {
                break;
            }
            if(count == 3)
                return true;
        }
        for(int d = 1; d < 4; d++) {
            if(col + d >= 7 || line + d >= 7)
                break;
            if(_grid[line + d][col + d] != _grid[line][col])
                break;
            if(d == 3)
                return true;
        }
        for(int d = 1; d < 4; d++) {
            if(col - d < 0 || line + d >= 7)
                break;
            if(_grid[line + d][col - d] != _grid[line][col])
                break;
            if(d == 3)
                return true;
        }
        return false;
    }
    ///
    /// Returns the 2 dimensions tabs with the tokens
    ///
    public int[][] getTokens() {
        // Copy to avoid modifications out of the class
        int[][] res = new int[7][7];
        for (int i = 0;i < _grid.length; i++) {
            for (int j = 0;j < _grid[i].length;j++) {
                res[i][j] = _grid[i][j];
            }
        }
        return res;
    }
}
