package oop.ex6.ifwhileanlyzer;

/**
 * exception in case of no arguments in if or while
 */
public class EmptyArgsIfWhileException extends IfWhileExceptions {

    /**
     * default constructor of the class
     */
    public EmptyArgsIfWhileException() {
        super("you arguments in if or while are empty");
    }
    /**
     * another constructor of the class
     */
    public EmptyArgsIfWhileException(String s){
        super(s);
    }
}
