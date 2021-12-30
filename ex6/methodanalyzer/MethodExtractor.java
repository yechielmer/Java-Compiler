package oop.ex6.methodanalyzer;

import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
import oop.ex6.blocktreeexctractor.CodeBlockNode;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is the factory which create the array of methods
 */
public class MethodExtractor {

    /**
     * this method create the map of methods
     * @return the map of methods
     */
    public static Map<String,Method> getMethods (List<CodeBlockNode> methods, List<String> codeLines) throws
            VarsExceptions, MethodExceptions {
        Map<String,Method> methodsMap=new HashMap<>();

        // identify the method and its arguments etc
        for (CodeBlockNode method:methods){
            Method current = MethodIdentifier.identifyMethodDeclaration(codeLines.get(method.getStart()),
                    method,codeLines);

            // exist method in map
            if (methodsMap.containsKey(current.getName())){
                throw new ExistMethodNameException();
            }
            methodsMap.put(current.getName(),current);
        }
        return methodsMap;
    }


}
