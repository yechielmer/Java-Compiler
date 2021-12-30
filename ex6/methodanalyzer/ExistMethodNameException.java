package oop.ex6.methodanalyzer;

/**
 * exception for case of two functions with same name
 */
public class ExistMethodNameException extends MethodExceptions {
    /**
     * the default constructor of the class
     */
    public ExistMethodNameException() {
        super("Exist two methods in same name");
    }
    /**
     * another constructor of the class
     */
    public ExistMethodNameException(String s){
        super(s);
    }
}
