
package othello;

import java.util.ArrayList;

/**
 *
 * @author Marietta E. Cameron
 */
public class Board {
    
    /**
     * Initializes board using myColor
     * 
     * @param myColor -- color of agent 
     */
 static char boardArray[] = new char[100];// the board
 static final int a = 10, b = 20, c = 30, d = 40, e = 50, f = 60, g = 70, h = 80;// use for referencing colum number
                                 ;
    Board(PieceColor myColor){        
        PieceColor turn = myColor;// color of player making moves
        // index location 0 --> border, 1-->opponent, 2 --> empty, 3 --> me
        for (int i = 0; i < 100; i++) {// Assign intial values of border and open spaces
           if (i <= 9 || i % 10 == 9 || i % 10 == 0 || i > 90) { // border locations
                boardArray[i] = '*';// border value 
            } else {
                boardArray[i] = '-'; //empty value
            }
        }
            if(turn == PieceColor.BLACK) {  // if I am the black player
                boardArray[d +5] = 'B';
                boardArray[e +4] = 'B';
                boardArray[e +5] = 'W';
                boardArray[d +4] = 'W';
            }else{// I am the white player
                boardArray[d +4] = 'W';
                boardArray[e +5] = 'W';
                boardArray[e +4] = 'B';
                boardArray[d +5] = 'B';
            }     
        
        
            
    }//constructor
    
    /**
     * 
     * @param oldBoard -- board to be copied
     */
    
    Board(Board oldBoard){
        
    }//Board
    
    /**
     * 
     * @param player -- player for whom to generate moves
     * @return List of all valid moves for player 
     */
    public ArrayList<Move> generateMoves(PlayerType player){
        return null;
    }//generateMoves
    
    /**
     * 
     * @param player  -- player for whom to apply move
     * @param amove   -- move to apply to board
     */
    public void applyMove(PlayerType player, Move amove){
        
    }//applyMove

    
    /**
     * Algorithm: 
     *      1. Generate Moves for ME   -- myMoves
     *      2. Generate Moves for My Opponent  -- oppMoves
     *      3. if myMoves.isEmpty and oppMoves.isEmpty
     *               isEndGame is true
     *          else
     *               isEndGame is false
     * @return true if game is over (ie: no moves for either player) 
     *         false otherwise
     */
    public boolean isEndGame(){
        return true;
    }
}//Board
