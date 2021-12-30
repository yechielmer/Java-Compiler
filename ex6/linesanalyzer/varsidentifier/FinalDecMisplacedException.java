package oop.ex6.linesanalyzer.varsidentifier;

/**
 * exception for illegal final exception
 */
public class FinalDecMisplacedException extends VarsExceptions{

        /**
         * default constructor of the class
         */
        public FinalDecMisplacedException () {
            super("You inserted Illegal final");
        }

        /**
         * another constructor of the class
         */
        public FinalDecMisplacedException (String s){
            super(s);
        }
    }


