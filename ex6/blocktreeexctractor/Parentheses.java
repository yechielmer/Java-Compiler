package oop.ex6.blocktreeexctractor;

/**
 * this class represent a parentheses
 */
public class Parentheses {
    /** 1 for opening parentheses, -1 for closing*/
    private int type;
    /** the line index of the parentheses*/
    private int index;
    /** opening parentheses keyword*/
    public static final int OPENER = 1;
    /** closing parentheses keyword*/
    public static final int CLOSURE = -1;

    /**
     * constructor for a parentheses
     * @param type type of the parentheses
     * @param index code line of the parentheses
     */
    public Parentheses(int type,int index){
        this.type = type;
        this.index = index;
    }

    /**
     *
     * @return the index of the parentheses
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @return the type of the parentheses
     */
    public int getType() {
        return type;
    }
}
