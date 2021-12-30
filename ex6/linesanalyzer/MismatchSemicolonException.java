package oop.ex6.linesanalyzer;

/**
 * exception in case of mismatch semicolon
 */
public class MismatchSemicolonException extends LineExceptions {
    public MismatchSemicolonException() {
        super("Your line has no semicolon");
    }
    /**
     * another constructor of the class
     */
    public MismatchSemicolonException(String s){
        super(s);
    }
}
