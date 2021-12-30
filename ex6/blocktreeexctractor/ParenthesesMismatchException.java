package oop.ex6.blocktreeexctractor;

/**
 * This is a general exception regarding the parentheses structure
 */
public class ParenthesesMismatchException extends TreeExceptions {


    /**
     * general constructor
     */
    public ParenthesesMismatchException() {
        super("parentheses not in order");
    }

    /**
     * string constructor
     *
     * @param s string to print in the exception
     */
    public ParenthesesMismatchException(String s) {
        super(s);
    }
}