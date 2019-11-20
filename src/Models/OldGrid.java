package Models;

import Utils.GridUtils;

public class OldGrid {
    ///
    /// The grid represents the game grid
    /// 0 = no token placed
    /// int > 0 = id of the player's token
    ///
    private int[][] _grid = new int[GridUtils.NbLine][GridUtils.NbCol];

    ///
    /// Constructor
    ///
    public OldGrid() {
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
    public void toArray(Grid grid){
        long bitboardP1 = grid.getBitboardPlayer1();
        long bitboardP2 = grid.getBitboardPlayer2();
        int nbTour = 0;
        for (int i=6;i>=0;i--){
            nbTour++;
            for(int j=0;j<6;j++){
                if(((bitboardP2>>48-(nbTour))%2)==1)
                    _grid[j][i]=1;
                if(((bitboardP1>>48-(nbTour))%2)==1)
                    _grid[j][i]=2;
                nbTour++;
            }
        }
    }
}
