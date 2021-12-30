package oop.ex6.methodanalyzer;

import oop.ex6.linesanalyzer.LineAnalyzer;
import oop.ex6.blocktreeexctractor.CodeBlockNode;
import oop.ex6.linesanalyzer.LineExceptions;
import oop.ex6.linesanalyzer.variables.Variable;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
import oop.ex6.main.Scope;
import oop.ex6.ifwhileanlyzer.IfWhileExceptions;
import oop.ex6.ifwhileanlyzer.IfWhileVerifier;

import java.util.List;
import java.util.Map;

/**
 * this class analyze of the method in the code.
 */
public class MethodAnalyzer {

    // the map of methods
    private Map<String, Method> methodMap;

    // an decorator of the line analyzer
    private LineAnalyzer lineAnalyzer;

    // and decorator of if and while verifier
    private IfWhileVerifier ifWhileVerifier;

    // the map of global vars
    private Map<String, Variable> globalVariables;

    /**
     * default constructor of the class, which get the global scope, the codeline and global variables
     * @param globalScope the global scope with its variables
     * @param codeLines the code lines to analyze
     * @param globalVariables the global variables list
     * @throws MethodExceptions general exceptions in case of error in method
     * @throws VarsExceptions general exceptions in case of error in vars
     */
    public MethodAnalyzer(CodeBlockNode globalScope, List<String> codeLines, Map<String, Variable> globalVariables) throws
            MethodExceptions, VarsExceptions {
        methodMap=MethodExtractor.getMethods(globalScope.getChildren(),codeLines);
        lineAnalyzer = new LineAnalyzer(codeLines,methodMap);
        ifWhileVerifier = new IfWhileVerifier(codeLines);//only for if while
        this.globalVariables = globalVariables;

    }

    /**
     * the main method of the class. its analyze the lines in method and call the inner scope if there are
     * @throws MethodExceptions general exceptions in case of error in method
     * @throws IfWhileExceptions general exceptions in case of error in if or while verifier
     * @throws LineExceptions general exceptions in case of errors in the line codes
     * @throws VarsExceptions general exceptions in case of error in the vars
     */
    public void analyze() throws MethodExceptions, IfWhileExceptions, LineExceptions, VarsExceptions {
        for(Method current : methodMap.values()){

            // analyze the current scope
            Scope methodScope = new Scope(current.getArgumentMap(),globalVariables);
            lineAnalyzer.setScope(methodScope);
            ifWhileVerifier.setScope(methodScope);

            // call to recursive function of the inner scopes
            analyzeHelper(current.getTree(),methodScope);
        }
    }

    // the recursive function which get code block and go to the inner scopes
    private void analyzeHelper(CodeBlockNode block,Scope currentScope) throws IfWhileExceptions,
            VarsExceptions, MethodExceptions,LineExceptions {
        if(block==null||block.getStart()> block.getEnd()){//base case
            return;
        }

        boolean[] codeLineIndexes = block.getCodeLineIndexes();// current scope indexes
        int index = block.getStart();
        int end = block.getEnd();
        boolean inChild = false;// flag to inform if we are encountring
        // a child in the first time after going over current block scope
        while (index<=end){// going over all lines
            if(codeLineIndexes[index-block.getStart()]){// the line is in the current scope
                inChild = false;
                lineAnalyzer.setLine(index);
                lineAnalyzer.analyze();
            }
            else {// we encountered a child
                if(!inChild){
                    inChild = true;
                    CodeBlockNode child =block.getChildInIndex(index);// getting the child
                    ifWhileVerifier.setLine(child.getStart());
                    ifWhileVerifier.verifyLine();
                    child.removeFirstAndLast();
                    currentScope.enterNewScope();// creating new scope for the child
                    analyzeHelper(child,currentScope);
                    currentScope.exitScope();// deleting the scope after existing the child
                }
            }
            index++;
        }
    }

}
