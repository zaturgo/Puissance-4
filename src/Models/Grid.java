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

    public int checkWin() {
        return 0;
    }
}
