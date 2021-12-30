package oop.ex6.ifwhileanlyzer;

import oop.ex6.linesanalyzer.AnalyzerUtils;
import oop.ex6.linesanalyzer.variables.VariableType;
import oop.ex6.main.Scope;

/**
 * this class get arguments of boolean and check if its legal
 */
public class BooleanVerifier {

    // regex for whitespace
    private static final String REGEX_WHITESPACE = "\\s{2,}";

    // regex for space
    private static final String SPACE = " ";

    // regex for operations
    private static final String REGEX_OPERATORS="[\\s]*(\\|\\|)[\\s]*|[\\s]*(\\&\\&)[\\s]*";

    // map of variables
    private Scope currentScope;

    // args line
    private String args;

    /**
     * the default constructor of the class which get arguments and check it
     * @param currentScope the scope to check in
     * @param line the line to check
     */
    public BooleanVerifier(Scope currentScope, String line){
        this.currentScope = currentScope;
        args=line;
        args=args.replaceAll(REGEX_WHITESPACE, SPACE);
        args= AnalyzerUtils.cleanWhiteSpaces(args);
    }

    /**
     * this method verify the arguments
     * @throws IfWhileExceptions some exceptions for the if while
     */
    public void verify() throws IfWhileExceptions{
        String[] parts=args.split(REGEX_OPERATORS);

        // there is no args
        if (parts.length==0){
            throw new EmptyArgsIfWhileException();
        }
        else{
            for (String part:parts){
                checkValue(part);
            }
        }
    }

    // check if the value in the arguments is legal
    private void checkValue (String arg) throws IfWhileExceptions{
        if (currentScope.getVariable(arg)!=null){
            // check assigned
            if(!currentScope.getVariable(arg).isAssigned()){
                throw new BadAssignedBooleanException();
            }
            else {
                // check compatible
                if(!VariableType.BOOLEAN.isTypeCompatible(currentScope.getVariable(arg).getType())){
                    throw new BadAssignedBooleanException();
                }
            }
        }
        else {
            if(!VariableType.BOOLEAN.isValueCompatible(arg)){
                throw new BadAssignedBooleanException();
        }
        }
    }
    }
