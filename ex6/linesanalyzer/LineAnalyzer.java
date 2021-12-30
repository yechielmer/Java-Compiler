package oop.ex6.linesanalyzer;

import oop.ex6.linesanalyzer.methodcall.FunctionCallIdentifier;
import oop.ex6.linesanalyzer.varsidentifier.VariableLineIdentifier;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
import oop.ex6.main.Scope;
import oop.ex6.methodanalyzer.Method;
import oop.ex6.methodanalyzer.MethodExceptions;
import oop.ex6.linesanalyzer.variables.Variable;


import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class get line and checks if it is function call or variable assign\declarex`
 */
public class LineAnalyzer {

    // regex for semicolon
    private static final String SUFFIX_OF_LINE = ";";

    // regex for an space
    private static final String SPACE_REGEX = "\\s";

    // regex for suffix of function
    private static final String SUFFIX_OF_FUNCTION = "^[\\w]+[\\s]*\\([^\\)]*\\)\\s*$";

    // regex for return regex
    private static final String RETURN_REGEX = "^[\\s]*return[\\s]*;[\\s]*";


    // map of methods
    private Map<String, Method> MethodMap;

    // map of variables
    private Scope currentScope;

    // the line working on
    private String currentLine;

    // are we in global or not
    private boolean global;

    //the code lines
    private List<String> codeLines;


    /**
     * the main constructor for analyze of an line
     * @param isGlobal flag to check if its global line or not
     * @param codeLines the line for codes to check
     */
    public LineAnalyzer(boolean isGlobal, List<String> codeLines){
        this.codeLines=codeLines;
        global=isGlobal;
        if(isGlobal){
            MethodMap = null;
        }
        currentScope = new Scope();


    }

    /**
     * constructor for non-global usage
     * @param codeLines
     */
    public LineAnalyzer(List<String> codeLines, Map<String, Method> methodMap) {
        this.codeLines=codeLines;
        this.global=false;
        this.MethodMap = methodMap;
    }

    /**
     * set the specific line to check
     * @param lineIndex the index of line to check
     */
    public void setLine(int lineIndex){
        currentLine= codeLines.get(lineIndex);
    }


    /**
     * change the scope to check
     * @param currentScope the scope to check
     */
    public void setScope(Scope currentScope){
        this.currentScope=currentScope;
    }


    /**
     * this is the main method which analyze the line
     * @throws LineExceptions exceptions in case of illegal line
     * @throws VarsExceptions exceptions in case of illegal variables
     * @throws MethodExceptions exceptions in case of illegal method
     */
    public void analyze() throws LineExceptions,
            VarsExceptions, MethodExceptions {

        // some main checks
        currentLine = AnalyzerUtils.cleanWhiteSpaces(currentLine);
        if (checkIfComment() || checkReturn()){
            return;
        }
        if(checkEmptyLine()){
            return;
        }
        checkSemicolon();
        Identifier identifier;


        // call of function
        if (checkIfCall()){
            identifier=new FunctionCallIdentifier(currentLine,MethodMap,currentScope);
        }

        // check variables
        else{
            identifier=new VariableLineIdentifier(global,currentLine, currentScope);

        }
        identifier.identify();

    }
    // check if comment
    private boolean checkIfComment() {
    return currentLine.startsWith("//");
    }

    // checks if the line ends with semicolon
    private void checkSemicolon() throws MismatchSemicolonException {

        if (!currentLine.endsWith(SUFFIX_OF_LINE)){
            throw new MismatchSemicolonException();
        }
        else{
            currentLine=currentLine.substring(0,currentLine.length()-1);
            currentLine=currentLine.trim();
        }
    }

    // check if line is empty or full of spaces
    private boolean checkEmptyLine (){
        Pattern p = Pattern.compile(SPACE_REGEX);
        Matcher m = p.matcher(currentLine);
        return  (currentLine.length()==0 || m.matches());
    }

    // check if line is return line
    private boolean checkReturn () throws ReturnGlobalException {
        Pattern p = Pattern.compile(RETURN_REGEX);
        Matcher m = p.matcher(currentLine);

        // return in global line
        if (m.matches() && global){
            throw new ReturnGlobalException();
        }
        return  m.matches();
    }

    // check if the its call to function
    private boolean checkIfCall(){
        return currentLine.matches(SUFFIX_OF_FUNCTION);
    }



    /**
     *
     * @return the global variables in an scope
     */
    public Map<String, Variable> getGlobalMap() {
        return currentScope.getGlobalScope();
    }
}
