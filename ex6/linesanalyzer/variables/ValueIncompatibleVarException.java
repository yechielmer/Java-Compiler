package oop.ex6.linesanalyzer.variables;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
/**
 * exception assign un compatible variable
 */
public class ValueIncompatibleVarException extends VarsExceptions {

    /**
     * the default constructor of the class
     */
    public ValueIncompatibleVarException() {
            super("The value is not compatible");
        }

        /**
         * another constructor of the class
         */
        public ValueIncompatibleVarException(String s){
            super(s);
        }
}
