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
    static public int threeAligned(int[][] tokens, int idPlayer){
        //if 2 aligned and 2 free horizontal XXOO
        for (int i = 0; i<GridUtils.NbLine; i++){
            int count = 0;
            for (int j = 0; j<GridUtils.NbCol; j++){
                if (tokens[i][j] == idPlayer){
                    count+=1;
                }else{
                    count = 0;
                }
                if (count ==2&&j<=4){
                    if (tokens[i][j+1]==0&&tokens[i][j+2]==0){
                        return j+1;
                    }
                }
            }
        }//                                X
        //if 2 aligned and 2 free vertical X
        for (int l=0;l<GridUtils.NbCol; l++){
            int countVert = 0;
            for (int k=0;k<GridUtils.NbLine; k++){
                if (tokens[k][l]==idPlayer){
                    countVert+=1;
                }else{
                    countVert=0;
                }
                if (countVert ==2&&k>=3){
                    if (tokens[k-2][l]==0&&tokens[k-3][l]==0){
                        return l;
                    }
                }
            }
        }                     //X     X
        //check the diagonals X O  et O X
        for (int m = 3; m<GridUtils.NbLine; m++){//we start at the second line
            for (int n=0; n<4; n++){//we end 2 col before end
                if (tokens[m][n]==idPlayer&&tokens[m-1][n+1]==idPlayer&&tokens[m-2][n+2]==0){
                    return n+2;
                }
            }
        }
        for (int m = 3; m<GridUtils.NbLine; m++){//we start at the second line
            for (int n=3; n<GridUtils.NbCol; n++){//we start at the third col(to avoid out of bounds)
                if (tokens[m][n]==idPlayer&&tokens[m-1][n-1]==idPlayer&&tokens[m-2][n-2]==0){
                    return n-2;
                }
            }
        }
        //check if two aligned with a hole X0X0 and 0X0X
        for (int m = 0; m<GridUtils.NbLine; m++){
            for (int n=0; n<4; n++){//we stop at the 4th col to avoid out of bounds
                if (tokens[m][n]==idPlayer&&tokens[m][n+1]==0&&tokens[m][n+2]==idPlayer&&tokens[m][n+2]==0){
                    return n+1;
                }
                if (tokens[m][n]==0&&tokens[m][n+1]==idPlayer&&tokens[m][n+2]==0&&tokens[m][n+2]==idPlayer){
                    return n;
                }
            }
        }
        return -1;
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
        for (int i = 0;i < NbLine; i++) {
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
    static public boolean opponentNotWinningNextTurn(int[][] tokens,int col, int playerId, int oplayerId) {
            int[][] tempGrid = GridUtils.copyGrid(tokens);
            GridUtils.placeToken(playerId, col, tempGrid);
            if (GridUtils.getNbPossibleWin(tempGrid, oplayerId) >= 1) return false;
            return true;
    }
}
