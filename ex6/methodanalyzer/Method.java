package oop.ex6.methodanalyzer;

import oop.ex6.linesanalyzer.variables.ValueIncompatibleVarException;
import oop.ex6.blocktreeexctractor.CodeBlockNode;
import oop.ex6.linesanalyzer.variables.Variable;
import oop.ex6.linesanalyzer.varsidentifier.MismatchAssignmentException;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is a wrapper for the code block, that adds method properties
 */
public class Method{
    // those are the arguments the method must get when calling*/
    private List<Variable> arguments;

    // this is the name of the method*/
    private String name;

    // this is the tree of the method content*/
    private CodeBlockNode tree;

    /**
     * the default constructor of the class
     * @param arguments the arguments of the method
     * @param name the name of the method
     * @param tree the tree we wrap in
     */
    public Method(List<Variable> arguments, String name,CodeBlockNode tree){
        this.arguments=arguments;
        this.name = name;
        this.tree=tree;
    }

    /**
     * this method checks if the provided vars are compatible with the method signeture
     * @param givenVars vars to check
     */
    public void checkArgumentCompatibility(List<Variable> givenVars) throws NotEnoughArgsException,
            VarsExceptions {

        // no same size of arguments
        if (givenVars.size()!= arguments.size()){
            throw new NotEnoughArgsException();
        }

        // check var
        for (int i=0; i<givenVars.size(); i++){

            // not assigned
            if(!givenVars.get(i).isAssigned()){
                throw new MismatchAssignmentException();
            }
            arguments.get(i).getType().isTypeCompatible(givenVars.get(i).getType());
        }
    }

    /**
     *
     * @return the name of the method
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the tree of the method
     */
    public CodeBlockNode getTree() {
        return tree;
    }

    /**
     *
     * @return the arguments of the method
     */
    public Map<String, Variable> getArgumentMap() {
        Map<String,Variable> argsMap = new HashMap<>();
        for(Variable arg:arguments){
            argsMap.put(arg.getName(),arg);
        }
        return argsMap;
    }
}
