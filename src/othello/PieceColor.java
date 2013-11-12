
package othello;

/**
 *
 * @author Archeress
 */
public enum PieceColor {
    BLACK('B'), WHITE('W'), EMPTY('-'), BORDER('*');
    private char value;
    PieceColor(char initValue){
        value = initValue;
    }//constructor
    @Override
    public String toString(){
        return value + "";
    }
}//PieceColor
