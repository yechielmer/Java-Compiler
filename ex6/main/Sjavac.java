package oop.ex6.main;

import oop.ex6.blocktreeexctractor.BlockParser;
import oop.ex6.blocktreeexctractor.CodeBlockNode;
import oop.ex6.blocktreeexctractor.TreeExceptions;
import oop.ex6.linesanalyzer.LineExceptions;
import oop.ex6.linesanalyzer.variables.Variable;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
import oop.ex6.ifwhileanlyzer.IfWhileExceptions;
import oop.ex6.methodanalyzer.MethodAnalyzer;
import oop.ex6.methodanalyzer.MethodExceptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * this is the main class of the program, which build the tree, and analyze the lines and methods
 */
public class Sjavac {

    // the message to print in case of an built in exception
    private static final String BUILT_IN_EXCEPTIONS_ERROR = "1";

    // the message to print in case of io exception
    private static final String IO_EXCEPTION_ERROR = "2";

    // the message to print in case of no exceptions
    private static final String NO_ERROR = "0";
    // the message to print in case io exception
    private static final String IO_EXCEPTION_MESSAGE = "Error reading file";

    /**
     * this is the main runner of the program, expects a file to verify
     * @param args file to verify
     */
    public static void main(String[] args) {
            try {
                // tree builder
                BlockParser parser = new BlockParser(args[0]);
                parser.generateTree();
                CodeBlockNode tree = parser.getTree();

                List<String> codeLines = parser.getCodeLines();
                // get globals
                Map<String, Variable> globalsMap = GlobalFactory.getGlobal(tree, codeLines);

                // analyze the method
                MethodAnalyzer methodAnalyzer = new MethodAnalyzer(tree, codeLines, globalsMap);
                methodAnalyzer.analyze();
            }
            catch (IOException e){
                System.out.println(IO_EXCEPTION_ERROR);
                System.err.println(IO_EXCEPTION_MESSAGE);
                return;
            }
            catch (VarsExceptions | MethodExceptions | IfWhileExceptions |
                    LineExceptions | TreeExceptions e){
                System.out.println(BUILT_IN_EXCEPTIONS_ERROR);
                System.err.println(e.getMessage());
                return;
            }
            System.out.println(NO_ERROR);



    }
}
