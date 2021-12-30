package oop.ex6.linesanalyzer.varsidentifier;

/**
 * exception for case of too short var
 */
public class TooShortVarException extends VarsExceptions {
    /**
     * default constructor of the class
     */
    public TooShortVarException () {
        super("Your var is too short");
    }

    /**
     * another constructor of the class
     */
    public TooShortVarException (String s){
        super(s);
    }
}
