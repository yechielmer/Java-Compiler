package oop.ex6.ifwhileanlyzer;

/**
 * exception in case of error in if and while
 */
public class IfWhileExceptions extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * the default constructor of the class
     */
    public IfWhileExceptions() {
        super("an error during if and while");
    }
    /**
     * another constructor of the class
     */
    public IfWhileExceptions(String s){
        super(s);
    }
}
