package oop.ex6.linesanalyzer.varsidentifier;

/**
 *  exception for illegal name for the var
 */
public class IllegalVarNameException extends VarsExceptions {
    /**
     * default constructor of the class
     */
    public IllegalVarNameException () {
        super("You inserted Illegal name");
    }

    /**
     * another constructor of the class
     */
    public IllegalVarNameException (String s){
        super(s);
    }
}
