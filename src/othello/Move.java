package othello;

/**
 *
 * @author cameron
 */
public class Move implements Comparable<Move>{
    static final int PASS_VALUE = 0;
    static private int pos;
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
        pos = movePos;
        value = moveValue;
    }//Move constructor
    
    Move (String moveStr){
         String[] sepMoveStr = moveStr.split(" ");
      value = (((int)sepMoveStr[1].toString().charAt(0) - 96) + (((int)sepMoveStr[2].toString().charAt(0) - 48) * 10 ));
      System.out.print(pos);
      pos = Integer.parseInt(sepMoveStr[2]);
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
