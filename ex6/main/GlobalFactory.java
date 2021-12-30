package oop.ex6.main;

import oop.ex6.linesanalyzer.LineAnalyzer;
import oop.ex6.blocktreeexctractor.CodeBlockNode;
import oop.ex6.linesanalyzer.LineExceptions;
import oop.ex6.linesanalyzer.methodcall.MismatchFunctionException;
import oop.ex6.linesanalyzer.variables.Variable;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
import oop.ex6.methodanalyzer.IllegalMethodDeclaration;
import oop.ex6.methodanalyzer.MethodExceptions;

import java.util.List;
import java.util.Map;

/**
 * this is the factory which create the array of the global variables
 */
public class GlobalFactory {

    /**
     * this method create the global variables
     * @param tree the tree of globals
     * @param codeLines the code lines to check
     * @return the arrayOfGlobals
     */

    public static Map<String, Variable> getGlobal (CodeBlockNode tree, List<String> codeLines) throws LineExceptions,
            VarsExceptions, MethodExceptions {

        // create an object of analyzer
        LineAnalyzer analyzer=new LineAnalyzer(true,codeLines);

        // get the list of globals
        boolean[] CheckList=tree.getCodeLineIndexes();
        for (int i=0; i<CheckList.length; i++){
            if (CheckList[i]){
                analyzer.setLine(i);
                analyzer.analyze();
            }
        }
        return analyzer.getGlobalMap();
    }


}
