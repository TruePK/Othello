package othello;
import java.io.*;
import java.util.Stack;
import java.util.LinkedList;
/**
 *
 * @author Marietta E. Cameron
 */
public class Othello {
   
    static char myPiece;
    Stack<Integer> moveStack = new Stack<Integer>();
    static boolean myTurn = false;
    static final int MyPieceINDEX= 3;  //index in pieces array
    static final int OppPieceINDEX = 1;  //index in pieces array
    static PieceColor[] pieces = {
                       PieceColor.BORDER, PieceColor.WHITE, PieceColor.EMPTY,PieceColor.BLACK};  
                       //0 --> border, 1-->opponent, 2 --> empty, 3 --> me
   
    PlayerType currentPlayer;   //Limits player types to the values OPPONENT and ME
    
    Board board;
    
    /* Initializes game variables (including board) based in the initialization string
     * @param initStr -- Initial string with values "I B" or "I W"
     *                   "I B" -- Initialize game with agent playing as Black
     *                   "I W" -- Initialize game with agent playeing as White
     */
    Othello(String initStr){
        if (initStr.charAt(2) == 'B') { 
            OthelloOut.printComment("JLM Black");
            board = new Board(PieceColor.BLACK);
            currentPlayer = PlayerType.ME;
            myTurn = false;
            pieces[OppPieceINDEX] = PieceColor.WHITE;
            pieces[MyPieceINDEX] = PieceColor.BLACK;
        }
        else {
            OthelloOut.printComment("JLm White");
            board = new Board(PieceColor.WHITE);
            currentPlayer = PlayerType.OPPONENT;
            myTurn = true;
            pieces[OppPieceINDEX] = PieceColor.BLACK;
            pieces[MyPieceINDEX] = PieceColor.WHITE;
        }    
        
    }
    boolean over(){
      for(int i = 0; i < 100; i++){
          if(Board.boardArray[i] == '-')
              return false;
      }
          return true;
    }
    public boolean legalMove(Move amove){
        boolean returnVal = false;
        String[] arrayLoc = amove.toString().split(" ");
        int col = ((Integer.parseInt(arrayLoc[1]))% 10) * 10;
        int row = ((((int)arrayLoc[0].charAt(0))-96));
        
        char currChar = Board.boardArray[(col)+row] ;
        
            if(currChar != '-'){
                System.out.print("C illegal\n");
                return false;
            }
            OthelloOut.printComment("Trying Validy!");
            if((Board.boardArray[(col)+(row+1)] == '-'||Board.boardArray[(col)+(row+1)] == '*')
                    && (Board.boardArray[(col)+(row-1)] == '-' ||Board.boardArray[(col)+(row-1)] == '*')
                    && (Board.boardArray[(col+10)+(row)] == '-' ||Board.boardArray[(col+10)+(row)] == '*')  
                    && (Board.boardArray[(col-10)+(row)] == '-' ||Board.boardArray[(col-10)+(row)] == '*')
                    && (Board.boardArray[(col+10)+(row-1)] == '-' ||Board.boardArray[(col+10)+(row-1)] == '*')
                    && (Board.boardArray[(col+10)+(row+1)] == '-'||Board.boardArray[(col+10)+(row+1)] == '*')
                    && (Board.boardArray[(col-10)+(row-1)] == '-'||Board.boardArray[(col-10)+(row-1)] == '*')
                    && (Board.boardArray[(col-10)+(row+1)] == '-'||Board.boardArray[(col-10)+(row+1)] == '*')){
                returnVal = false;
                System.out.print("C illegal\n");
            }
            OthelloOut.printComment("Stating Scan...");
            //OthelloOut.printComment("-->!");
            int j = 0;
            if((pieces[MyPieceINDEX].toString().charAt(0) == Board.boardArray[(col)+(row+1)])){//--->
                int numOfAdds = 0;
                for(int i = 0; i<9;i++){
                    if(Board.boardArray[(col)+(row+i)] != '*' && Board.boardArray[(col)+(row+i)] != '-' ){
                        if(Board.boardArray[(col)+(row+i)] == pieces[MyPieceINDEX].toString().charAt(0)){
                            moveStack.push(col+(row+i));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col)+(row+i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            i = 10;
                            returnVal = true;
                        }
                    }else{
                        for(j=j; j<numOfAdds;j++){
                            if(moveStack.size() != 0){
                            moveStack.pop();
                            }
                                i=100;
                            
                        }
                    }
                    }
                }
            
            j = 0;
            //OthelloOut.printComment("<----");
            if((pieces[MyPieceINDEX].toString().charAt(0) == Board.boardArray[(col)+(row-1)])){//<---
                int numOfAdds = 0;
                for(int i = 0; i<9;i++){
                    if(Board.boardArray[(col)+(row-i)] != '*' && Board.boardArray[(col)+(row-i)] != '-' ){
                        if(Board.boardArray[(col)+(row-i)] == pieces[MyPieceINDEX].toString().charAt(0)){
                            moveStack.push(col+(row-i));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col)+(row-i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            i = 10;
                            returnVal = true;
                        }
                    }else{
                        
                        for( j = j; j<numOfAdds;j++){
                            if(moveStack.size() != 0){
                            moveStack.pop();
                            }
                                i=100;
                                                    }
                    }
                }
            j = 0;}
            //OthelloOut.printComment("V");
            if((pieces[MyPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row)])){//Down 
                int numOfAdds = 0;
                for(int i = 10; i<(100-col);i=i+10){
                    if(Board.boardArray[(col+i)+(row)] != '*' && Board.boardArray[(col+i)+(row)] != '-' ){
                        if(Board.boardArray[(col+i)+(row)] == pieces[MyPieceINDEX].toString().charAt(0)){
                            moveStack.push((col+i)+(row));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col+ i)+(row)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            i = 100-col;
                            returnVal=true;
                        }
                    }else{
                        for(j = j; j<numOfAdds;j=j+1){
                            if(moveStack.size() != 0){
                            moveStack.pop();
                            }
                                i=100;
                                                    }
                    }
                }
            }
            j = 0;
            //OthelloOut.printComment("^");
            if((pieces[MyPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row)])){//UP 
                int numOfAdds = 0;
                for(int i = 10; i<(100-(100-col));i=i+10){
                    if(Board.boardArray[(col-i)+(row)] != '*' && Board.boardArray[(col-i)+(row)] != '-' ){
                        if(Board.boardArray[(col-i)+(row)] == pieces[MyPieceINDEX].toString().charAt(0)){
                            moveStack.push((col-i)+(row));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col- i)+(row)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            i = 100-(100-col);
                            returnVal=true;
                        }
                    }else{
                        for(j = j; j<numOfAdds;j=j+1){
                            if(moveStack.size() != 0){
                            moveStack.pop();
                            }
                                i=100;
                                                    }
                    }
                }
            }
            j = 0;
            //OthelloOut.printComment("^Right");
            if((pieces[MyPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row+1)])){//UP&Right 
                int numOfAdds = 0;
                for(int i = 10; i<col;i=i+10){
                    if(Board.boardArray[(col-i)+(row+(i/10))] != '*' && Board.boardArray[(col-i)+(row+(i/10))] != '-' ){
                        if(Board.boardArray[(col-i)+(row+(i/10))] == pieces[MyPieceINDEX].toString().charAt(0)){
                            moveStack.push((col-i)+(row+(i/10)));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col- i)+(row)+(i/10)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            i = 100-(100-col);
                            returnVal=true;
                        }
                    }else{
                        for(j = j; j<numOfAdds;j=j+1){
                            if(moveStack.size() != 0){
                            moveStack.pop();
                            }
                                i=100;
                            
                        }
                    }
                }
            }
            j = 0;
            //OthelloOut.printComment("^Left");
            if((pieces[MyPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row-1)])){//UP&LEFT
                int numOfAdds = 0;
                for(int i = 10; i<col;i=i+10){
                    if(Board.boardArray[(col-i)+(row-(i/10))] != '*' && Board.boardArray[(col-i)+(row-(i/10))] != '-' ){
                        if(Board.boardArray[(col-i)+(row-(i/10))] == pieces[MyPieceINDEX].toString().charAt(0)){
                            moveStack.push((col-i)+(row-(i/10)));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col- i)+(row-(i/10))] == pieces[OppPieceINDEX].toString().charAt(0)){
                            i = 100;
                            returnVal=true;
                        }
                    }else{
                        for(j = j; j<numOfAdds;j=j+1){
                            if(moveStack.size() != 0){
                            moveStack.pop();
                            }
                                i=100;
                            
                        }
                    }
                }
            }
            j = 0;
            //OthelloOut.printComment("VRight");
            if((pieces[MyPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row+1)])){//DOWN&Right
                int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(((col+(10*i))+(row+i)) < 89 && Board.boardArray[(col+(10*i))+(row+i)] != '*' && Board.boardArray[(col+(10*i))+(row+i)] != '-' ){
                        if(Board.boardArray[(col+(10*i))+(row+i)] == pieces[MyPieceINDEX].toString().charAt(0)){
                            moveStack.push((col+(10*i))+(row+i));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col+(10*i))+(row+i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            i = 100;
                            returnVal=true;
                        }
                    }else{
                        for(j = j; j<numOfAdds;j=j+1){
                            if(moveStack.size() != 0){
                            moveStack.pop();
                            }
                                i=100;
                            
                        }
                    }
                    }
                }
            
            
            j = 0;
            //OthelloOut.printComment("VLEFT");
            if((pieces[MyPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row-1)])){//DOWN&LEFT
                int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(Board.boardArray[(col+(10*i))+(row-i)] != '*' && Board.boardArray[(col+(10*i))+(row-i)] != '-' ){
                        if(Board.boardArray[(col+(10*i))+(row-i)] == pieces[MyPieceINDEX].toString().charAt(0)){
                            moveStack.push((col+(10*i))+(row-i));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col+(10*i))+(row-i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            i = 100;
                            returnVal=true;
                        }
                    }else{
                        for(j = j; j<numOfAdds;j++){ 
                            if(moveStack.size() != 0){
                            moveStack.pop();
                            }
                                i=100;     
                        }
                    }
                }
            }
            
            j = 0;
        if(returnVal == false){
            OthelloOut.printComment("Found No Move");
            return false;
        }else{
            OthelloOut.printComment("Got A Move");
            return true;
        }
            
            }
        
    
    boolean myTurn(){//return true if currently my turn false otherwise
        return myTurn;
    }
    Move getMyMove() {
        
       moveStack.removeAll(moveStack);
        int pos = 0;
        Move[] moveToMake = new Move[100];
        
        for(int col = 11; col <=81; col= col + 10){
          for(int row = 0; row<=7;row++){
              
            if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//---> 
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col)+(row+i)] != '*' && Board.boardArray[(col)+(row+i)] != '-' ){
                        if(Board.boardArray[(col)+(row+i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            moveStack.push((col)+(row+i));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col)+(row+i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//<---
                 int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col)+(row-i)] != '*' && Board.boardArray[(col)+(row-i)] != '-' ){
                        if(Board.boardArray[(col)+(row-i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                           moveStack.push((col)+(row-i));
                        numOfAdds++;
                        }
                        if(Board.boardArray[(col)+(row-i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP
                 int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(((col-(10*i))+(row)) > 11 && Board.boardArray[(col-(10*i))+(row)] != '*' && Board.boardArray[(col-(10*i))+(row)] != '-' ){
                        if(Board.boardArray[((col-(10*i))+(row))] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row));
                                numOfAdds++;
                        }
                        if(Board.boardArray[(col-(10*i))+(row)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(((col+(10*i))+(row)) < 89 && Board.boardArray[(col+(10*i))+(row)] != '*' && Board.boardArray[(col+(10*i))+(row)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN & right
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col+(10*i))+(row+i)] != '*' && Board.boardArray[(col+(10*i))+(row+i)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row+i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row+i));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row+i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN & Right
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(Board.boardArray[(col+(10*i))+(row-i)] != '*' && Board.boardArray[(col+(10*i))+(row-i)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row-i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row-i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP & right
                        int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(((col-(10*i))+(row+i)) > 11 && Board.boardArray[(col-(10*i))+(row+i)] != '*' && Board.boardArray[(col-(10*i))+(row+i)] != '-' ){
                        if(Board.boardArray[(col-(10*i))+(row+i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row+i));
                                numOfAdds++;
                        }
                        if(Board.boardArray[(col-(10*i))+(row+i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP & Left
                      int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(((col-(10*i))+(row-i)) > 11 && Board.boardArray[(col-(10*i))+(row-i)] != '*' && Board.boardArray[(col-(10*i))+(row-i)] != '-' ){
                       if(Board.boardArray[((col-(10*i))+(row-i))] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col-(10*i))+(row-i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
        return moveToMake[0 + (int)(Math.random()*(pos-1))];
        
    }
    
    void getMyMove(int col, int row) {
        moveStack.removeAll(moveStack);
        int pos = 0;
        Move[] moveToMake = new Move[15];
        
        //for(int col = 11; col <=81; col= col + 10){
          //for(int row = 0; row<=7;row++){
              
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//---> 
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(Board.boardArray[(col)+(row+i)] != '*' && Board.boardArray[(col)+(row+i)] != '-' ){
                        if(Board.boardArray[(col)+(row+i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            moveStack.push((col)+(row+i));
                            numOfAdds++;
                        }
                        if(Board.boardArray[(col)+(row+i)] == pieces[MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
                            pos++;
                            i=100;
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
              
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//<---
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col)+(row-i)] != '*' && Board.boardArray[(col)+(row-i)] != '-' ){
                        if(Board.boardArray[(col)+(row-i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                            moveStack.push((col)+(row-i));
                        numOfAdds++;
                        }
                        if(Board.boardArray[(col)+(row-i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col-(10*i))+(row)] != '*' && Board.boardArray[((col-(10*i))+(row))] != '-' ){
                        if(Board.boardArray[(col-(10*i))+(row)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row));
                                numOfAdds++;
                        }
                        if(Board.boardArray[(col-(10*i))+(row)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN
                  int numOfAdds = 0;
                for(int i = 1; i<=8;i++){
                    if(Board.boardArray[(col+(10*i))+(row)] != '*' && Board.boardArray[(col+(10*i))+(row)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row)] == pieces[MyPieceINDEX].toString().charAt(0)){
                            Move move = new Move(col,row);
                            moveToMake[pos] =  move;
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
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN & right
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col+(10*i))+(row+i)] != '*' && Board.boardArray[(col+(10*i))+(row+i)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row+i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row+i));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row+i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col+10)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//DOWN & Left
                  int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col+(10*i))+(row-i)] != '*' && Board.boardArray[(col+(10*i))+(row-i)] != '-' ){
                       if(Board.boardArray[(col+(10*i))+(row-i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col+(10*i))+(row-i));
                                numOfAdds++;
                       }
                        if(Board.boardArray[(col+(10*i))+(row-i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row+1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP & right
                        int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col-(10*i))+(row+i)] != '*' && Board.boardArray[(col-(10*i))+(row+i)] != '-' ){
                        if(Board.boardArray[(col-(10*i))+(row+i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row+i));
                                numOfAdds++;
                        }
                        if(Board.boardArray[(col-(10*i))+(row+i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              if((pieces[OppPieceINDEX].toString().charAt(0) == Board.boardArray[(col-10)+(row-1)]) 
                      && Board.boardArray[(col)+(row)] == '-'){//UP & Left
                      int numOfAdds = 0;
                for(int i = 1; i<=7;i++){
                    if(Board.boardArray[(col-(10*i))+(row-i)] != '*' && Board.boardArray[(col-(10*i))+(row-i)] != '-' ){
                       if(Board.boardArray[(col-(10*i))+(row-i)] == pieces[OppPieceINDEX].toString().charAt(0)){
                                moveStack.push((col-(10*i))+(row-i));
                                numOfAdds++;
                       }
                       
                        if(Board.boardArray[(col-(10*i))+(row-i)] == pieces[MyPieceINDEX].toString().charAt(0)){
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
              
                    
             
              
        //  }
              
       // }
        
        //return moveToMake[0 + (int)(Math.random()*(pos-1))];
        
    }
    
    boolean validSyntax(String moveStr){
        if (moveStr.length() < 1)
            return false;
        if (moveStr.charAt(0) != pieces[OppPieceINDEX].toString().charAt(0))
            return false;
        if (moveStr.length()==1)
            return true;
        if (moveStr.length() > 5)
            return false;
        
        if ((moveStr.charAt(2) < 'a') && (moveStr.charAt(2) > 'h'))
            return false;
        if ((moveStr.charAt(4) < '1') && (moveStr.charAt(4) > '8'))
            return false;        
        return true;      
    }//validSyntax
    /**
     * Read a move string from keyboard (ie from opponent) and return the corresponding move
     * @param keyboard
     * @return 
     */
    Move getOpponentMove(BufferedReader keyboard) {
        try{
            String read = keyboard.readLine();
            while (!validSyntax(read)){
                 OthelloOut.printComment("Invalid Move.");
                 read = keyboard.readLine();
            }
            if(read.length() != 1){
                OthelloOut.printComment("Got a read");
                Move move = new Move(read);
                return move;
            }else{
                OthelloOut.printComment("Got a Pass");
                Move move = new Move(pieces[OppPieceINDEX].toString() + " z -1");
                return move;
            }
            
            
            
        }catch(IOException e){
            
        }
        
        return null;
    }
    void applyMove(Move move){
                 String[] arrayLoc = move.toString().split(" ");

        if((((int)arrayLoc[0].charAt(0))) != 'z' && (((int)arrayLoc[0].charAt(0))) != -1){
        int pos = ((Integer.parseInt(arrayLoc[1])) * 10) + ((((int)arrayLoc[0].charAt(0))-96));
        if(legalMove(move) == true){
            OthelloOut.printComment("Got a legal Move!");
            int size = moveStack.size();
            for(int i = 0; i <size;i++){
                
            Board.boardArray[moveStack.pop()] = pieces[OppPieceINDEX].toString().charAt(0);
            }
            Board.boardArray[pos] = pieces[OppPieceINDEX].toString().charAt(0);
            moveStack.removeAll(moveStack);
           
        }else{   
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                move = getOpponentMove(keyboard);
                applyMove(move);
        
                }}
    }
        
    
void applyMoveAI(Move move){
            if(move != null){
        String[] arrayLoc = move.toString().split(" ");
        int row = (((int)arrayLoc[0].charAt(0))-96);
        int col = (Integer.parseInt(arrayLoc[1]))*10;
        getMyMove(col,row);
        Board.boardArray[((col)+row)] = pieces[MyPieceINDEX].toString().charAt(0);
            int size = moveStack.size();
        for(int i = 1; i <= size;i++){
            Board.boardArray[moveStack.pop()] = pieces[MyPieceINDEX].toString().charAt(0);
            }
        moveStack.removeAll(moveStack);
         OthelloOut.printMove(pieces[MyPieceINDEX], move);
            }else{
               System.out.print(pieces[MyPieceINDEX].toString() + "\r\n");
                
            }
          
        }

    
    void printBoard(){
        int Arrayint = 0;
        int row = 0;
        int currlev = 49;
        int numLev = 97;
        System.out.print("C   ");
        
        for (int i = 0; i <8; i++)
            System.out.print((char)(numLev + i) + " ");
            System.out.print("\n");
        while(row != 10){
            row++;
            System.out.print("C");
            for(int i = Arrayint; i <= 9 + Arrayint && i < 100; i++){
                System.out.print("|" + board.boardArray[i]);
            }
            if(Arrayint >= 10 && Arrayint < 90){
                System.out.print("|"+ (char)currlev  +"\n");
                currlev++;
            }else{
                System.out.print("|\n");
            }
                Arrayint += 10;
        }
    }
    void switchPlayers(){
        if(myTurn == false){
            myTurn = true;
        }else
            myTurn = false;  
    }
    
    void announceScore(){
        int white = 0; int black = 0;
        for(int i = 0; i < 100; i++){
            if(Board.boardArray[i] == 'W'){
                white++;
            }
            if(Board.boardArray[i] == 'B'){
                white++;
            }
        }
        OthelloOut.printComment("White Captured: " + white+ " Black Captured: " + black);
       
    }
    
   /**
     * @param args the command line arguments
   */
    public static void main(String[] args) throws IOException {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        OthelloOut.printComment("Please enter I B or I W:");
        String initializeStr = keyboard.readLine();
        myPiece = initializeStr.charAt(2);
        Othello game = new Othello(initializeStr); //Initialize game        
        game.printBoard();
        OthelloOut.printReady(pieces[MyPieceINDEX]);  //let referee know that I'm readly to play
       Move move;

        while (!game.over()) {
            if (game.myTurn() == false){
                char myChar = pieces[MyPieceINDEX].toString().charAt(0);
                char oppChar = pieces[OppPieceINDEX].toString().charAt(0);
                OthelloOut.printComment("Start AI");
                Stack<Move> moveToMake = new Stack<Move>();
                Stack<Integer> posToTurn = new Stack<Integer>();
                moveToMake = game.board.findMoves(myPiece, oppChar, game.board);
                move = game.board.getRandomMoveFromStack(moveToMake);
                posToTurn= game.board.genrateMove(myPiece, oppChar, game.board, move);
                game = game.board.applyMove(game, move,posToTurn);
                game.printBoard();
                game.switchPlayers();
            }else{
                char myChar = pieces[OppPieceINDEX].toString().charAt(0);
                char oppChar = pieces[MyPieceINDEX].toString().charAt(0);
                OthelloOut.printComment("Start with OPP");
                move = game.getOpponentMove(keyboard);
                game.applyMove(move);
                game.printBoard();
                game.switchPlayers();
            }
        }//while game is not over
        game.announceScore();
    }//main
}//Othello