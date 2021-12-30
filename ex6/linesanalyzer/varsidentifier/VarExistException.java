package oop.ex6.linesanalyzer.varsidentifier;

/**
 * exception of trying to add exist variable
 */
public class VarExistException extends VarsExceptions {
    /**
     * default constructor of the class
     */
    public VarExistException() {
        super("Your var already exist");
    }

    /**
     * another constructor of the class
     */
    public VarExistException(String s){
        super(s);
    }
}
