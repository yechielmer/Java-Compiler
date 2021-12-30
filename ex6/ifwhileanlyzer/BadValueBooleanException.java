package oop.ex6.ifwhileanlyzer;

public class BadValueBooleanException extends IfWhileExceptions {

    /**
     * default constructor of the class
     */
    public BadValueBooleanException() {
        super("Your boolean is illegal");
    }
    /**
     * another constructor of the class
     */
    public BadValueBooleanException(String s){
        super(s);
    }
}
