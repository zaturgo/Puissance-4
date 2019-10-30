package Utils;

import Models.Grid;

import java.util.ArrayList;

public class GridUtils {
    public static final int NbLine = 6;
    public static final int NbCol = 7;

    ///
    /// Returns 0 if none wins
    /// Otherwise it returns the winner's playerId
    ///
    static public int checkWin(int[][] tokens) {
        for (int i = 0;i < tokens.length; i++) {
            for (int j = 0;j < tokens[i].length;j++) {
                if(tokens[i][j] != 0) {
                    if (GridUtils.fourAligned(i, j, tokens))
                        return tokens[i][j];
                }
            }
        }
        return 0;
    }
    static public boolean isWinningMove(int col, Grid grid, int playerId) {
        ArrayList<Integer> tokens[] = grid.newGetTokens();
        int line = tokens[col].size() - 1;
        // Check vert
        for(int i = 1; (line - i) >= 0 ; i++) {
            if(!(tokens[col].get(line - i) == playerId))
                break;
            if(i == 2) // == 2 because next turn i will be equal to 3
                return true;
        }
        // Check lines
        int count = 0;
        for(int i = -3; i < 3; i++) {
            if(col + i >= NbCol || line < 0)
                break;
            if(col + i < 0 || tokens[col + i].size() == 0) {
                count = 0;
                continue;
            }
            if(tokens[col + i].get(line - 1) == playerId) {
                count++;
                if(count == 3)
                    return true;
            }
            else
                count = 0;
        }
        return false;
    }
    ///
    /// Look if 3 tokens are aligned next to the one placed in [line][col]
    ///
    static private boolean fourAligned(int line, int col, int[][] tokens) {
        for(int count = 0; count < 4; count++) {
            if (line + count >= NbLine)
                break;
            if (tokens[line + count][col] != tokens[line][col]) {
                break;
            }
            if (count == 3)
                return true;
        }
        for(int count = 0; count < 4; count++) {
            if(col + count >= NbCol)
                break;
            if(tokens[line][col+count] != tokens[line][col]) {
                break;
            }
            if(count == 3)
                return true;
        }
        for(int d = 1; d < 4; d++) {
            if(col + d >= 7 || line + d >= NbLine)
                break;
            if(tokens[line + d][col + d] != tokens[line][col])
                break;
            if(d == 3)
                return true;
        }
        for(int d = 1; d < 4; d++) {
            if(col - d < 0 || line + d >= NbLine)
                break;
            if(tokens[line + d][col - d] != tokens[line][col])
                break;
            if(d == 3)
                return true;
        }
        return false;
    }
    // Copy the grid
    static public int[][] copyGrid(int[][] tokens) {
        int[][] res = new int[NbLine][NbCol];
        for (int i = 0;i < NbLine; i++) {
            for (int j = 0;j < NbCol;j++) {
                res[i][j] = tokens[i][j];
            }
        }
        return res;
    }
    ///
    /// Place the token in the grid
    /// If the token can't be placed, throw an InvalidArgumentException
    ///
    static public boolean placeToken(int playerId, int col, int[][] tokens) {
        for(int i = tokens.length-1; i >= 0; --i) {
            if(tokens[i][col] == 0) {
                tokens[i][col] = playerId;
                return true;
            }
        }
        return false;
    }
    ///
    /// Show the grid in the console for debug (before vue)
    ///
    static public void debugGrid(int[][] tokens) {
        // Init grid to 0
        for (int i = 0;i < NbCol; i++) {
            for (int j = 0;j < tokens[i].length;j++) {
                System.out.print(tokens[i][j]);
            }
            System.out.println();
        }
    }
    ///
    /// Returns -1 if no col found
    ///
    static public int getWinnableCol(int[][] tokens, int playerId) {
        for (int i = 0; i < NbCol; i++) {
            int[][] tempGrid = GridUtils.copyGrid(tokens);
            GridUtils.placeToken(playerId, i, tempGrid);
            if(GridUtils.checkWin(tempGrid) != 0) {
                return i;
            }
        }
        return -1;
    }
    static public int getNbPossibleWin(int[][] tokens, int playerId) {
        int count = 0;
        for (int i = 0; i < NbCol; i++) {
            int[][] tempGrid = GridUtils.copyGrid(tokens);
            GridUtils.placeToken(playerId, i, tempGrid);
            // see if the other player can win next turn
            if(GridUtils.checkWin(tempGrid) != 0) {
                count++;
            }
        }
        return count;
    }
    ///
    /// Return -1 if no unavoidable win next turn
    ///
    static public int getUnavoidableWinNextTurn(int[][] tokens, int playerId) {
        for (int i = 0; i < NbCol; i++) {
            int[][] tempGrid = GridUtils.copyGrid(tokens);
            GridUtils.placeToken(playerId, i, tempGrid);
            if(GridUtils.getNbPossibleWin(tempGrid, playerId) >= 2)
                return i;
        }
        return -1;
    }
}
