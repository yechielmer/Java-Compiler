package oop.ex6.linesanalyzer;

/**
 * this is the super class for exceptions in the line
 */
public class LineExceptions extends Exception {
    private static final long serialVersionUID = 1L;
    public LineExceptions() {
        super("Your line has a problem");
    }
    /**
     * another constructor of the class
     */
    public LineExceptions(String s){
        super(s);
    }
}

