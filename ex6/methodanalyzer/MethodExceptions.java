package oop.ex6.methodanalyzer;

/**
 * exception for errors in the method
 */
public class MethodExceptions extends Exception{
    private static final long serialVersionUID = 1L;
    /**
     * the default constructor of the class
     */
    public MethodExceptions() {
        super("There is an problem in the method");
    }
    /**
     * another constructor of the class
     */
    public MethodExceptions(String s){
        super(s);
    }
}
