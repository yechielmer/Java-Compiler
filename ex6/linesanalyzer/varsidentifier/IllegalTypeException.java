package oop.ex6.linesanalyzer.varsidentifier;

/**
 * exception for illegal type
 */
public class IllegalTypeException extends VarsExceptions{

        /**
         * default constructor of the class
         */
        public IllegalTypeException () {
            super("You inserted Illegal Type");
        }

        /**
         * another constructor of the class
         */
        public IllegalTypeException (String s){
            super(s);
        }
}
