package oop.ex6.linesanalyzer.methodcall;

import oop.ex6.linesanalyzer.LineExceptions;

/**
 * exception for calling un exist function
 */
public class MismatchFunctionException extends LineExceptions {

    public MismatchFunctionException() {
        super("The function does not exist");
    }

    /**
     * another constructor of the class
     */
    public MismatchFunctionException(String s){
        super(s);
    }
}
