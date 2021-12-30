package oop.ex6.methodanalyzer;

/**
 * exception in case of illegal method declaration
 */
public class IllegalMethodDeclaration extends MethodExceptions{
    /**
     * the default constructor of the class
     */
    public IllegalMethodDeclaration() {
        super("The declare of the method is illegal");
    }
    /**
     * another constructor of the class
     */
    public IllegalMethodDeclaration(String s){
        super(s);
    }
}
