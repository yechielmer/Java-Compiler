package oop.ex6.linesanalyzer.varsidentifier;

/**
 * exception for mismatch assignment value
 */
public class MismatchAssignmentException extends VarsExceptions {
    /**
     * default constructor of the class
     */
    public MismatchAssignmentException () {
        super("this is mismatch assignment");
    }

    /**
     * another constructor of the class
     */
    public MismatchAssignmentException (String s){
        super(s);
    }
}

