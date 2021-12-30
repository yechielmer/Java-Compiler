package oop.ex6.ifwhileanlyzer;

/**
 * exception in case of trying to assign un assigned value to boolean
 */
public class BadAssignedBooleanException extends IfWhileExceptions {

    /**
     * default constructor of the class
     */
    public BadAssignedBooleanException() {
        super("Your boolean is not assigned");
    }
    /**
     * another constructor of the class
     */
    public BadAssignedBooleanException(String s){
        super(s);
    }
}
