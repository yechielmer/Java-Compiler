package oop.ex6.methodanalyzer;

/**
 * exception for case which there is not enough arguments in method call
 */
public class NotEnoughArgsException extends MethodExceptions{
    /**
     * the default constructor of the class
     */
    public NotEnoughArgsException() {
        super("You call function with no enough variables");
    }
    /**
     * another constructor of the class
     */
    public NotEnoughArgsException(String s){
        super(s);
    }
}
