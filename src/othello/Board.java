
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
    
    public Stack<Move> findMoves(char mine,char opp, Board board){
        //Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) = opp
        //mine = Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)
        
       moveStack.removeAll(moveStack);
        int pos = 0;
        Stack<Move> moveToMake = new Stack<Move>();
        
        for(int col = 11; col <=81; col= col + 10){
          for(int row = 0; row<=7;row++){
              
            if((opp == board.boardArray[(col)+(row+1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//---> 
                  int numOfAdds = 0;
                for(int i = 1; i<=9;i++){
                    if(board.boardArray[(col)+(row+i)] != '*' && board.boardArray[(col)+(row+i)] != '-' ){
                        if(board.boardArray[(col)+(row+i)] == opp){
                            moveStack.push((col)+(row+i));
                            numOfAdds++;
                        }
                        if(board.boardArray[(col)+(row+i)] == mine){
                            Move move = new Move(col,row);
                            moveToMake.addElement(move);
                            pos++;
                            i=10;
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
              
              if((opp == board.boardArray[(col)+(row-1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//<---
                 int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(board.boardArray[(col)+(row-i)] != '*' && board.boardArray[(col)+(row-i)] != '-' ){
                        if(board.boardArray[(col)+(row-i)] == opp){
                           moveStack.push((col)+(row-i));
                        numOfAdds++;
                        }
                        if(board.boardArray[(col)+(row-i)] == mine){
                            Move move = new Move(col,row);
                             moveToMake.addElement(move);
                            pos++;
                            i=9;
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
              
              if((opp == board.boardArray[(col-10)+(row)]) 
                      && board.boardArray[(col)+(row)] == '-'){//UP
                 int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col-(10*i))+(row)) > 11 && board.boardArray[(col-(10*i))+(row)] != '*' && board.boardArray[(col-(10*i))+(row)] != '-' ){
                        if(board.boardArray[((col-(10*i))+(row))] == opp){
                                moveStack.push((col-(10*i))+(row));
                                numOfAdds++;
                        }
                        if(board.boardArray[(col-(10*i))+(row)] == mine){
                            Move move = new Move(col,row);
                             moveToMake.addElement(move);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        
                    }
                }
              }
              
              if((opp == board.boardArray[(col+10)+(row)]) 
                      && board.boardArray[(col)+(row)] == '-'){//DOWN
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col+(10*i))+(row)) < 89 && board.boardArray[(col+(10*i))+(row)] != '*' && board.boardArray[(col+(10*i))+(row)] != '-' ){
                       if(board.boardArray[(col+(10*i))+(row)] == opp){
                                moveStack.push((col+(10*i))+(row));
                                numOfAdds++;
                       }
                        if(board.boardArray[(col+(10*i))+(row)] == mine){
                            Move move = new Move(col,row);
                             moveToMake.addElement(move);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              
              if((opp == board.boardArray[(col+10)+(row+1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//DOWN & right
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(board.boardArray[(col+(10*i))+(row+i)] != '*' && board.boardArray[(col+(10*i))+(row+i)] != '-' ){
                       if(board.boardArray[(col+(10*i))+(row+i)] == opp){
                                moveStack.push((col+(10*i))+(row+i));
                                numOfAdds++;
                       }
                        if(board.boardArray[(col+(10*i))+(row+i)] == mine){
                            Move move = new Move(col,row);
                             moveToMake.addElement(move);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((opp == board.boardArray[(col+10)+(row-1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//DOWN & Right
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(board.boardArray[(col+(10*i))+(row-i)] != '*' && board.boardArray[(col+(10*i))+(row-i)] != '-' ){
                       if(board.boardArray[(col+(10*i))+(row-i)] == opp){
                                moveStack.push((col+(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(board.boardArray[(col+(10*i))+(row-i)] == mine){
                            Move move = new Move(col,row);
                             moveToMake.addElement(move);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((opp == board.boardArray[(col-10)+(row+1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//UP & right
                        int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col-(10*i))+(row+i)) > 11 && board.boardArray[(col-(10*i))+(row+i)] != '*' && board.boardArray[(col-(10*i))+(row+i)] != '-' ){
                        if(board.boardArray[(col-(10*i))+(row+i)] == opp){
                                moveStack.push((col-(10*i))+(row+i));
                                numOfAdds++;
                        }
                        if(board.boardArray[(col-(10*i))+(row+i)] == mine){
                            Move move = new Move(col,row);
                             moveToMake.addElement(move);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((opp == board.boardArray[(col-10)+(row-1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//UP & Left
                      int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col-(10*i))+(row-i)) > 11 && board.boardArray[(col-(10*i))+(row-i)] != '*' && board.boardArray[(col-(10*i))+(row-i)] != '-' ){
                       if(board.boardArray[((col-(10*i))+(row-i))] == opp){
                                moveStack.push((col-(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(board.boardArray[(col-(10*i))+(row-i)] == mine){
                            Move move = new Move(col,row);
                             moveToMake.addElement(move);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }  
          }
        }
        moveStack.removeAll(moveStack);
        return moveToMake;    
    }//FindMoves
    
    public Move getRandomMoveFromStack(Stack<Move> moveToMake){
        if(moveToMake.size() > 0){
        int pos =  0 + (int)(Math.random()*(moveToMake.size()-1));
        return moveToMake.elementAt(pos);
        }
        return null;
    }
    
        public Stack<Integer> genrateMove(char mine,char opp, Board board,Move move){
        //Othello.pieces[Othello.OppPieceINDEX].toString().charAt(0) = opp
        //mine = Othello.pieces[Othello.MyPieceINDEX].toString().charAt(0)
            if(move!=null){
        
       moveStack.removeAll(moveStack);
        int pos = 0;
        Stack<Move> moveToMake = new Stack<Move>();
        
        String[] arrayLoc = move.toString().split(" ");
        int row = (((int)arrayLoc[0].charAt(0))-96);
        int col = (Integer.parseInt(arrayLoc[1]))*10;
 
            if((opp == board.boardArray[(col)+(row+1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//---> 
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(board.boardArray[(col)+(row+i)] != '*' && board.boardArray[(col)+(row+i)] != '-' ){
                        if(board.boardArray[(col)+(row+i)] == opp){
                            moveStack.push((col)+(row+i));
                            numOfAdds++;
                        }
                        if(board.boardArray[(col)+(row+i)] == mine){
                            Move tempMove = new Move(col,row);
                            moveToMake.addElement(tempMove);
                            pos++;
                            i=9;
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
              
              if((opp == board.boardArray[(col)+(row-1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//<---
                 int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(board.boardArray[(col)+(row-i)] != '*' && board.boardArray[(col)+(row-i)] != '-' ){
                        if(board.boardArray[(col)+(row-i)] == opp){
                           moveStack.push((col)+(row-i));
                        numOfAdds++;
                        }
                        if(board.boardArray[(col)+(row-i)] == mine){
                            Move tempMove = new Move(col,row);
                            moveToMake.addElement(tempMove);
                            pos++;
                            i=9;
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
              
              if((opp == board.boardArray[(col-10)+(row)]) 
                      && board.boardArray[(col)+(row)] == '-'){//UP
                 int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col-(10*i))+(row)) > 11 && board.boardArray[(col-(10*i))+(row)] != '*' && board.boardArray[(col-(10*i))+(row)] != '-' ){
                        if(board.boardArray[((col-(10*i))+(row))] == opp){
                                moveStack.push((col-(10*i))+(row));
                                numOfAdds++;
                        }
                        if(board.boardArray[(col-(10*i))+(row)] == mine){
                            Move tempMove = new Move(col,row);
                            moveToMake.addElement(tempMove);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        
                    }
                }
              }
              
              if((opp == board.boardArray[(col+10)+(row)]) 
                      && board.boardArray[(col)+(row)] == '-'){//DOWN
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col+(10*i))+(row)) < 89 && board.boardArray[(col+(10*i))+(row)] != '*' && board.boardArray[(col+(10*i))+(row)] != '-' ){
                       if(board.boardArray[(col+(10*i))+(row)] == opp){
                                moveStack.push((col+(10*i))+(row));
                                numOfAdds++;
                       }
                        if(board.boardArray[(col+(10*i))+(row)] == mine){
                            Move tempMove = new Move(col,row);
                            moveToMake.addElement(tempMove);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              
              if((opp == board.boardArray[(col+10)+(row+1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//DOWN & right
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(board.boardArray[(col+(10*i))+(row+i)] != '*' && board.boardArray[(col+(10*i))+(row+i)] != '-' ){
                       if(board.boardArray[(col+(10*i))+(row+i)] == opp){
                                moveStack.push((col+(10*i))+(row+i));
                                numOfAdds++;
                       }
                        if(board.boardArray[(col+(10*i))+(row+i)] == mine){
                            Move tempMove = new Move(col,row);
                            moveToMake.addElement(tempMove);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((opp == board.boardArray[(col+10)+(row-1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//DOWN & Right
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(board.boardArray[(col+(10*i))+(row-i)] != '*' && board.boardArray[(col+(10*i))+(row-i)] != '-' ){
                       if(board.boardArray[(col+(10*i))+(row-i)] == opp){
                                moveStack.push((col+(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(board.boardArray[(col+(10*i))+(row-i)] == mine){
                            Move tempMove = new Move(col,row);
                            moveToMake.addElement(tempMove);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((opp == board.boardArray[(col-10)+(row+1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//UP & right
                        int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col-(10*i))+(row+i)) > 11 && board.boardArray[(col-(10*i))+(row+i)] != '*' && board.boardArray[(col-(10*i))+(row+i)] != '-' ){
                        if(board.boardArray[(col-(10*i))+(row+i)] == opp){
                                moveStack.push((col-(10*i))+(row+i));
                                numOfAdds++;
                        }
                        if(board.boardArray[(col-(10*i))+(row+i)] == mine){
                            Move tempMove = new Move(col,row);
                            moveToMake.addElement(tempMove);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }
              if((opp == board.boardArray[(col-10)+(row-1)]) 
                      && board.boardArray[(col)+(row)] == '-'){//UP & Left
                      int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col-(10*i))+(row-i)) > 11 && board.boardArray[(col-(10*i))+(row-i)] != '*' && board.boardArray[(col-(10*i))+(row-i)] != '-' ){
                       if(board.boardArray[((col-(10*i))+(row-i))] == opp){
                                moveStack.push((col-(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(board.boardArray[(col-(10*i))+(row-i)] == mine){
                            Move tempMove = new Move(col,row);
                            moveToMake.addElement(tempMove);
                            pos++;
                            i=9;
                        }
                    }else{
                        i = 9;
                        for(int j = 1; j <=numOfAdds;j++){
                            moveStack.pop();
                        }
                        //moveStack.removeAll(moveStack);
                    }
                }
              }  
              return moveStack;
            }
            return moveStack;
          }
    /**
     * 
     * @param player  -- player for whom to apply move
     * @param amove   -- move to apply to board
     */
    public Othello applyMove(Othello game, Move move,Stack<Integer> moveStack,int sender,boolean printIt){
        OthelloOut.printComment("Start Apply");
            if(move != null || moveStack.size() != 0){
        String[] arrayLoc = move.toString().split(" ");
        int row = (((int)arrayLoc[0].charAt(0))-96);
        int col = (Integer.parseInt(arrayLoc[1]))*10; 
        
        game.board.boardArray[((col)+row)] = game.pieces[sender].toString().charAt(0);
            int size = moveStack.size();
        for(int i = 1; i <= size;i++){
            game.board.boardArray[moveStack.pop()] = game.pieces[sender].toString().charAt(0);
            }
        moveStack.removeAll(moveStack);
         if(printIt == true){
         OthelloOut.printMove(game.pieces[sender], move);
            }
        }
            if(move == null && printIt == true){
         System.out.print(game.pieces[sender].toString() + "\r\n");
        }
        return game;
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
