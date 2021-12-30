package oop.ex6.main;

import oop.ex6.linesanalyzer.variables.Variable;

import java.util.*;

/**
 * this class represent an scope which has list of hash map of variables
 */
public class Scope {

   //is global scope
    private boolean isGlobal;

    private LinkedList<Map<String, Variable>> variableScope;
    Map<String, Variable> globalVariables;

    public Scope(Map<String, Variable> firstScope,Map<String, Variable> globalVariables){
        this.variableScope = new LinkedList<>();
        variableScope.add(firstScope);
        this.globalVariables = new HashMap<>();
        duplicateGlobal(globalVariables);
    }

    /**
     * constructor for global use
     */
    public Scope(){
        this.globalVariables = new HashMap<>();
        this.variableScope = new LinkedList<>();
        this.isGlobal=true;
    }

    private void duplicateGlobal(Map<String, Variable> globalVariables) {
        for(Variable var: globalVariables.values()){
            this.globalVariables.put(var.getName(), new Variable(var));
        }
    }

    /**
     * this method enter new scope by creating new map
     */
    public void enterNewScope(){
        variableScope.add(new HashMap<>());
    }

    /**
     * this method exist scope by removing the map
     */
    public void exitScope(){
        variableScope.removeLast();
    }
    /**
     * return the variable with given name, if one does not exist return null
     * @param varName the name of the variable
     * @return the variable if exist, null otherwise
     */
    public Variable getVariable (String varName){

        // in case of global scope, check only in the global variables
        if (isGlobal){
            return globalVariables.get(varName);
        }

        // iterate over all the hash map
        Iterator<Map<String, Variable>> scopeIter = variableScope.descendingIterator();
        while(scopeIter.hasNext()){
            Map<String, Variable> currentScope =scopeIter.next();
            if(currentScope.containsKey(varName)){
                return currentScope.get(varName);
            }
        }
        return globalVariables.get(varName);
    }


    /**
     * this method checks if the last map contains an variable
     * @param name the name of the variable
     * @return true if exist, false otherwise
     */
    public boolean lastScopeContains(String name) {
        return variableScope.getLast().containsKey(name);
    }

    /**
     * this method add variable to the last map (the current scope-map)
     * @param variable the variable to add
     */
    public void add(Variable variable) {

        // in case of global scope, adding to the global scope
        if (isGlobal){
            globalVariables.put(variable.getName(), variable);
            return;
        }
        variableScope.getLast().put(variable.getName(),variable);
    }

    /**
     *
     * @return the global scope, in case of global scpope
     */
    public Map<String, Variable> getGlobalScope() {
        return globalVariables;
    }
}
