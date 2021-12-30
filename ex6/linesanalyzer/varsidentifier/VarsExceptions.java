package oop.ex6.linesanalyzer.varsidentifier;

/**
 * the super class of all the variables exceptions
 */
public class VarsExceptions extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * default constructor of the class
     */
    public VarsExceptions () {
        super("Your var has a problem");
    }

    /**
     * another constructor of the class
     */
    public VarsExceptions (String s){
        super(s);
    }
}
