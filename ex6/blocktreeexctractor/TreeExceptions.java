package oop.ex6.blocktreeexctractor;

/**
 * this is the general exception for the tree module
 */
public class TreeExceptions extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * the default constructor of the class
     */
    public TreeExceptions() {
        super("an error during creating the tree");
    }
    /**
     * another constructor of the class
     */
    public TreeExceptions(String s){
        super(s);
    }
}

