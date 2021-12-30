package oop.ex6.linesanalyzer.methodcall;

import oop.ex6.linesanalyzer.AnalyzerUtils;
import oop.ex6.linesanalyzer.Identifier;
import oop.ex6.linesanalyzer.variables.Variable;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
import oop.ex6.main.Scope;
import oop.ex6.methodanalyzer.Method;
import oop.ex6.methodanalyzer.NotEnoughArgsException;
import oop.ex6.methodanalyzer.IllegalMethodDeclaration;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class represent identify of call of function
 */
public class FunctionCallIdentifier implements Identifier {

    // the regex of extract of variables
    private static final String EXTRACT_REGEX = "\\((.*?)\\)";

    // the regex of split of variables
    private static final String SPLIT_OF_VARS = "[\\s]*,[\\s]*";

    // the regex for call of function
    public static final String FUNCTION_CALL_REGEX = "^([\\w\\d_]+)[\\s]*(\\([\\w\\d', .\")]*\\))[\\s]*$";

    // map of methods
    private final Map<String, Method> mapOfMethods;

    //  this is the map of local variables known in this scope*/
    private final Scope currentScope;

    // current line
    private String currentLine;

    /**
     * the main consturctor of the class
     * @param line the line to check
     * @param methodMap the method of map
     * @param currentScope the scope to check for
     */
    public FunctionCallIdentifier(String line,  Map<String, Method> methodMap,Scope currentScope){
        currentLine=line;
        mapOfMethods=methodMap;
        this.currentScope=currentScope;
    }

    @Override
    public void identify() throws MismatchFunctionException, VarsExceptions,
            IllegalMethodDeclaration, NotEnoughArgsException {
        String methodName =checkExist ();
        String vars =extractVar();
        checkCompatible(methodName, vars);
    }

    // checks if the function exist
    private String checkExist() throws MismatchFunctionException {

        Matcher m = Pattern.compile(FUNCTION_CALL_REGEX).matcher(currentLine);
        m.matches();

        // not exsit map
        if (mapOfMethods==null || !mapOfMethods.containsKey(m.group(1))){
            throw new MismatchFunctionException();
        }

        currentLine=m.group(2);
        return m.group(1);

    }

    // extract the var and identify
    private String extractVar() throws IllegalMethodDeclaration {
        Matcher m = Pattern.compile(EXTRACT_REGEX).matcher(currentLine);
        if (m.find()) {
            return m.group(1);
        }
        else{
            throw new IllegalMethodDeclaration();
        }
    }


    // check if there is enough vars and if its compatible
    private void checkCompatible(String methodName, String vars) throws NotEnoughArgsException,
            VarsExceptions {
        vars= AnalyzerUtils.cleanWhiteSpaces(vars);
        List<Variable> givenVariables= new LinkedList<>();

        // check length
        if (vars.length()!=0){
            String[] parts = vars.split(SPLIT_OF_VARS);

            // take the arguments
            for (String var:parts){
                if(currentScope.getVariable(var)!=null){
                    givenVariables.add(currentScope.getVariable(var));
                }
                else {
                    givenVariables.add(new Variable(var));
                }
            }
        }

        // check compatibility
        Method method = mapOfMethods.get(methodName);
        method.checkArgumentCompatibility(givenVariables);
    }

}
