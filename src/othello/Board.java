
package othello;

import java.util.ArrayList;
import java.util.Stack;

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
 Stack<Integer> moveStack = new Stack<Integer>();
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
    public Stack<Integer> generateMoves(PlayerType player){
        
       moveStack.removeAll(moveStack);
        int pos = 0;
        Move[] moveToMake = new Move[100];
        
        for(int col = 11; col <=81; col= col + 10){
          for(int row = 0; row<=7;row++){
              
            if((Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//---> 
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col)+(row+i)] != '*' && Board.boardArray[(col)+(row+i)] != '-' ){
                        if(Board.boardArray[(col)+(row+i)] == Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0)){
                            moveStack.push((col)+(row+i));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col)+(row+i)] == Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=8;
                        }
                    }else{
                        i=9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              
              if((Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//<---
                 int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col)+(row-i)] != '*' && Board.boardArray[(col)+(row-i)] != '-' ){
                        if(Board.boardArray[(col)+(row-i)] == Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0)){
                           moveStack.push((col)+(row-i));
                        numOfAdds++;
                        }
                        if(Board.boardArray[(col)+(row-i)] == Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=8;
                        }
                    }else{
                        i=8;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              
              if((Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP
                 int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(((col-(10*i))+(row)) > 11 && Board.boardArray[(col-(10*i))+(row)] != '*' && Board.boardArray[(col-(10*i))+(row)] != '-' ){
                        if(Board.boardArray[((col-(10*i))+(row))] == Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row));
                                numOfAdds++;
                        }
                        if(Board.boardArray[(col-(10*i))+(row)] == Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=8;
                        }
                    }else{
                        i = 8;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        
                    }
                }
              }
              
              if((Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(((col+(10*i))+(row)) < 89 && Board.boardArray[(col+(10*i))+(row)] != '*' && Board.boardArray[(col+(10*i))+(row)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row)] == Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row)] == Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=8;
                        }
                    }else{
                        i = 8;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              
              if((Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN & right
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col+(10*i))+(row+i)] != '*' && Board.boardArray[(col+(10*i))+(row+i)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row+i)] == Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row+i));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row+i)] == Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=8;
                        }
                    }else{
                        i = 8;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN & Right
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(Board.boardArray[(col+(10*i))+(row-i)] != '*' && Board.boardArray[(col+(10*i))+(row-i)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row-i)] == Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row-i)] == Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=8;
                        }
                    }else{
                        i = 8;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP & right
                        int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(((col-(10*i))+(row+i)) > 11 && Board.boardArray[(col-(10*i))+(row+i)] != '*' && Board.boardArray[(col-(10*i))+(row+i)] != '-' ){
                        if(Board.boardArray[(col-(10*i))+(row+i)] == Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row+i));
                                numOfAdds++;
                        }
                        if(Board.boardArray[(col-(10*i))+(row+i)] == Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=8;
                        }
                    }else{
                        i = 8;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP & Left
                      int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(((col-(10*i))+(row-i)) > 11 && Board.boardArray[(col-(10*i))+(row-i)] != '*' && Board.boardArray[(col-(10*i))+(row-i)] != '-' ){
                       if(Board.boardArray[((col-(10*i))+(row-i))] == Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col-(10*i))+(row-i)] == Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=8;
                        }
                    }else{
                        i = 8;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }  
          }
        }
        
        return moveStack;    
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
