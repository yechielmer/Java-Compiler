package oop.ex6.linesanalyzer.variables;

import java.util.*;
import java.util.function.Predicate;

/**
 * this class represent an enum of the type of the variable
 */
public enum VariableType {
    STRING(new HashSet<>(),value -> (value.startsWith("\""))&&value.endsWith("\"")&&value.length()>=2),

    CHAR(new HashSet<>(),value -> (value.startsWith("\'"))&&value.endsWith("\'")&&value.length()==3),

    INT(new HashSet<>(),value -> value.matches("^[-+]?[0-9]+$")),

    DOUBLE(new HashSet<>(Arrays.asList(INT)),value->value.matches("^[-+]?[0-9]+.[0-9]+$|^[0-9]+$")),

    BOOLEAN(new HashSet<>(Arrays.asList(INT,DOUBLE)),value->(value.equals("true")||value.equals("false")));

    //storing types accepted to assign by this type*/
    private Set<VariableType> acceptedTypes;

    //test predicate to check value compatibilty*/
    Predicate<String> acceptedValues;

    /** receives all the supported types bt this type*/
    VariableType(Set<VariableType> acceptedTypes, Predicate<String> acceptedValues) {
        this.acceptedTypes = acceptedTypes;
        this.acceptedValues=acceptedValues;
    }

    /**
     * this method returns if the given type can by assigned to the current type
     * @param type type to check
     * @return true if accepted
     */
    public boolean isTypeCompatible(VariableType type) {
        if(type==this){
            return true;
        }
        return acceptedTypes.contains(type);
    }

    /**
     * this method check if an value is compatible with some type
     * @param value the value to check if compatible
     * @return true if compatible, false otherwise
     */
    public boolean isValueCompatible(String value){
        for (VariableType compatibleType:acceptedTypes){
            if (compatibleType.acceptedValues.test(value)){
                return true;
            }
        }
        return acceptedValues.test(value);
    }

    /**
     * get some type and checks if its legal
     * @param varType the type to checl
     * @return true if legal type, false otherwise
     */
    public static boolean checkLegalType(String varType) {
        boolean flag;
        switch (varType) {
            case BOOLEAN_KEYWORD:
            case STRING_KEYWORD:
            case DOUBLE_KEYWORD:
            case CHAR_KEYWORD:
            case INT_KEYWORD:
                flag=true;;
                break;
            default:
                flag=false;
        }
        return flag;
    }

    /*constants*/

    /**
     * the name of "char" variable
     */
    public static final String CHAR_KEYWORD = "char";

    /**
     * the name of "double" variable
     */
    public static final String DOUBLE_KEYWORD = "double";

    /**
     * the name of "int" variable
     */
    public static final String INT_KEYWORD = "int";

    /**
     * the name of "string" variable
     */
    public static final String STRING_KEYWORD = "String";

    /**
     * the name of "boolean" variable
     */
    public static final String BOOLEAN_KEYWORD = "boolean";


}
