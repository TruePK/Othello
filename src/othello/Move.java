package othello;

/**
 *
 * @author cameron
 */
public class Move implements Comparable<Move>{
    static final int PASS_VALUE = 0;
    static private char pos;
    static private int value;
    /**
     * Default constructor that initializes a pass move
     */
    Move(){
        pos = PASS_VALUE;
    }//Move default contructor
    /**
     * 
     * @param movePos -- parameter for initial value for Move's instance variables 
     * @param moveValue -- parameter for value of move
     */
    Move(int movePos, int moveValue){
       int currPoss = movePos + moveValue;
        pos = ((char)((currPoss%10)+96));//a-h
        value = ((currPoss/10));//1-8
    }//Move constructor
    
    Move (String moveStr){
         String[] sepMoveStr = moveStr.split(" ");
      pos = (sepMoveStr[1].toString().charAt(0));
      value = (Integer.parseInt(sepMoveStr[2]));
      if(moveStr.length() == 1){
          value = -1;
          pos = 'z';
      }
    }
    /**
     * 
     * @param oldMove -- Move to be copied 
     */
    Move(Move oldMove){
        
    }//Move clone constructor
    
    /**
     * 
     * @return string value of Move 
     */
    @Override
    public String toString(){
        return (pos + " " + value);
    }//toString
    
    /**
     * 
     * @param otherMove -- move to be compared 
     * @return 
     *      -1 if this move precedes otherMove
     *       0 if this move equals otherMove
     *       1 if this move succeeds otherMove
     */
    @Override
    public int compareTo(Move otherMove){
        return 0;
    }//compareTo
}//Move
