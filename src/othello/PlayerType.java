
package othello;

/**
 *
 * @author Archeress
 */
public enum PlayerType {
    OPPONENT(-1), ME(1);
        private int value;
        PlayerType(int initValue){
            value = initValue;
        }//Constructor
        /*
         * @return the integer value of player element
         */
        int getValue(){
            return value;
        }   
}//PlayerType
