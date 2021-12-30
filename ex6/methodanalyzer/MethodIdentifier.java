package oop.ex6.methodanalyzer;


import oop.ex6.linesanalyzer.varsidentifier.ArgumentIdentifier;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
import oop.ex6.blocktreeexctractor.CodeBlockNode;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class get method, checks if legal and extract its arguments
 */
public class MethodIdentifier {

    // regex for the method declaration
    private static final String METHOD_REGEX =
            "^[\\s]*void[\\s]+([^\\d_][\\w]*)[\\s]*\\(([\\w\\s]*[,[\\w\\s]*]*)\\)[\\s]*\\{[\\s]*$";

    // regex for the arguments delimiter
    private static final String ARGUMENT_DELIMITER = ",\\s*";

    // regex for the return line
    private static final String RETURN_REGEX = "^[\\s]*return[\\s]*;[\\s]*$";

    // regex for an empty line
    private static final String EMPTY_LINE = "^[\\s]*$";

    /**
     * this is the main metho of the class, which identify the method and check if legal
     * @param methodDec the line of declaration of the method
     * @param methodTree the tree which the method belong to
     * @param codeLines the code lines of the method
     * @return the method object if its legal
     * @throws IllegalMethodDeclaration exception in case of illegal name
     * @throws VarsExceptions exception in case of error in the vars
     */
    public static Method identifyMethodDeclaration(String methodDec, CodeBlockNode methodTree, List<String> codeLines)
            throws IllegalMethodDeclaration, VarsExceptions {

        // regex of the line
        int returnIndex = findLastReturn(codeLines,methodTree);
        Pattern p = Pattern.compile(METHOD_REGEX);
        Matcher m = p.matcher(methodDec);

        // illegal declaration
        if(!m.matches()){
            throw new IllegalMethodDeclaration();
        }
        String methodName = m.group(1);
        String[] arguments = m.group(2).split(ARGUMENT_DELIMITER);

        // check arguments
        ArgumentIdentifier identifier = new ArgumentIdentifier();
        for (String argument : arguments) {
            identifier.setLine(argument);
            identifier.identify();
        }

        // remove the line of declaration
        methodTree.removeMethodDec(returnIndex);

        // create the method
        return new Method(identifier.getArgumentList(),methodName,methodTree);

    }

    // check if the last line in the code lines(without empty line etc) is return
    private static int findLastReturn(List<String> codeLines, CodeBlockNode methodTree) throws IllegalMethodDeclaration {
        int index = methodTree.getEnd()-1;
        int start = methodTree.getStart()+1;
        while(codeLines.get(index).matches(EMPTY_LINE) && index>start){
            index--;
        }
        if(!codeLines.get(index).matches(RETURN_REGEX)){
            throw new IllegalMethodDeclaration();
        }
        return index;
    }
}
