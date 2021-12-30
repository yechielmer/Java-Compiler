package oop.ex6.ifwhileanlyzer;

import oop.ex6.linesanalyzer.AnalyzerUtils;
import oop.ex6.main.Scope;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class get line of if or while and verify if it legal
 */
public class IfWhileVerifier {
    // current line to verify*/
    private int currentLine;

    // code lines of the document*/
    private List<String> codeLines;

    // regex string for if while*/
    private static String ifWhileRegex = "^[\\s]*(if|while)[\\s]*\\(([\\w|&\\s]+)\\)[\\s]*\\{[\\s]*$";

    // current scope of verification*/
    private Scope currentScope;


    /**
     * this id the deafult constructor
     * @param codeLines line of the document
     */
    public IfWhileVerifier(List<String> codeLines){
        this.codeLines=codeLines;
    }

    /**
     * set the current scope
     * @param currentScope the scope to set
     */
    public void setScope(Scope currentScope){
        this.currentScope=currentScope;
    }

    /**
     * this method sets the line to verify
     * @param lineIndex the line to set
     */
    public void setLine(int lineIndex){
        currentLine=lineIndex;
    }

    /**
     * the main method of the class, to verify the line
     * @throws IfWhileExceptions general exceptions in some cases
     */
    public void verifyLine() throws IfWhileExceptions {

        // regex for the line
        String line = AnalyzerUtils.cleanWhiteSpaces(codeLines.get(currentLine));
        Matcher m = Pattern.compile(ifWhileRegex).matcher(line);
        if(!m.matches()){
            throw new IfWhileExceptions();
        }

        // verify the boolean
        BooleanVerifier verifier = new BooleanVerifier(currentScope,m.group(2));
        verifier.verify();
    }

}
