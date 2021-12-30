package oop.ex6.linesanalyzer;

/**
 * exception in case of return in global line
 */
public class ReturnGlobalException extends LineExceptions {
    public ReturnGlobalException() {
        super("You can't return in global line");
    }
    /**
     * another constructor of the class
     */
    public ReturnGlobalException(String s){
        super(s);
    }
}
