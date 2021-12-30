package oop.ex6.linesanalyzer.variables;

import oop.ex6.linesanalyzer.varsidentifier.IllegalTypeException;

/**
 * this class represent an variable on the lines
 */
public class Variable {

    // the name of the variable
    private String name;

    // data member to set type
    private VariableType type;
    // data member to check if assigned
    private boolean assignment;

    // data member to check if final
    private boolean isFinal;

    // data member to check if global
    private boolean isGlobal;

    /***
     * the main constructor of variable
     * @param NameOfVariable the name of the variable
     * @param typeOfVariable the type of the variable
     * @param checkIfGlobal check if the variable is global
     * @param checkIfFinal check if the variable is final
     * @throws IllegalTypeException
     */
    public Variable(String NameOfVariable, String typeOfVariable, Boolean checkIfGlobal, Boolean checkIfFinal)
            throws IllegalTypeException {
        name = NameOfVariable;
        parseType(typeOfVariable);
        isGlobal = checkIfGlobal;
        isFinal = checkIfFinal;
        assignment = false;
    }

    /**
     * Special constructor for temp variable by value
     *
     * @param value value to add
     */
    public Variable(String value) throws IllegalTypeException {
        setTypeByValue(value);
        isGlobal = false;
        isFinal = false;
        assignment = true;
    }

    /**
     * copy constructor
     *
     * @param toCopy variable to copy
     */
    public Variable(Variable toCopy) {
        name = toCopy.name;
        type = toCopy.type;
        assignment = toCopy.assignment;
        isFinal = toCopy.isFinal;
        isGlobal = toCopy.isGlobal;
    }

    /**
     * change the value of the variable
     *
     * @param value the value to chagne to
     */
    public void checkValue(String value) throws ValueIncompatibleVarException {
        if (!type.isValueCompatible(value)) {
            throw new ValueIncompatibleVarException();
        }
    }

    // this function set type of the variable by his value
    private void setTypeByValue(String value) throws IllegalTypeException {
        if (VariableType.DOUBLE.isValueCompatible(value)) {
            type = VariableType.DOUBLE;
        } else if (VariableType.INT.isValueCompatible(value)) {
            type = VariableType.INT;
        } else if (VariableType.CHAR.isValueCompatible(value)) {
            type = VariableType.CHAR;
        } else if (VariableType.STRING.isValueCompatible(value)) {
            type = VariableType.STRING;
        } else if (VariableType.BOOLEAN.isValueCompatible(value)) {
            type = VariableType.BOOLEAN;
        } else {
            throw new IllegalTypeException();
        }
    }

    /**
     * @return the type of the variable
     */
    public VariableType getType() {
        return type;
    }

    /**
     * change the name of the variable
     *
     * @param var the name to change to
     */
    public void setName(String var) {
        name = var;
    }

    /**
     * this method gets the raw type and sets the relevent enum
     *
     * @param varType the type of the variable
     */
    private void parseType(String varType) throws IllegalTypeException {
        switch (varType) {
            case VariableType.BOOLEAN_KEYWORD:
                type = VariableType.BOOLEAN;
                break;
            case VariableType.CHAR_KEYWORD:
                type = VariableType.CHAR;
                break;
            case VariableType.DOUBLE_KEYWORD:
                type = VariableType.DOUBLE;
                break;
            case VariableType.INT_KEYWORD:
                type = VariableType.INT;
                break;
            case VariableType.STRING_KEYWORD:
                type = VariableType.STRING;
                break;
            default:
                throw new IllegalTypeException();
        }

    }

    /**
     * this method returns the name of the variable
     *
     * @return anme of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * assign an value to the variable
     */
    public void assign() {
        assignment = true;
    }

    /**
     * @return the assignment state of the variable
     */
    public boolean isAssigned() {
        return assignment;
    }

    /**
     * @return the final state of the variable
     */
    public boolean getFinal() {
        return isFinal;
    }
}
