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
    /*
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
*/
    boolean myTurn(){//return true if currently my turn false otherwise
        return myTurn;
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
                Move move = new Move();
                move = null;
                return move;
            }
            
            
            
        }catch(IOException e){
            
        }
        
        return null;
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
                game = game.board.applyMove(game, move,posToTurn,MyPieceINDEX,true);
                game.printBoard();
                game.switchPlayers();
            }else{
                char myChar = pieces[OppPieceINDEX].toString().charAt(0);
                char oppChar = pieces[MyPieceINDEX].toString().charAt(0);
                Stack<Integer> posToTurn = new Stack<Integer>();
                OthelloOut.printComment("Start with OPP");
                
                move = game.getOpponentMove(keyboard);
                posToTurn= game.board.genrateMove(myChar, oppChar, game.board, move);
                game = game.board.applyMove(game, move,posToTurn,OppPieceINDEX,false);
                game.printBoard();
                game.switchPlayers();
            }
        }//while game is not over
        game.announceScore();
    }//main
}//Othello