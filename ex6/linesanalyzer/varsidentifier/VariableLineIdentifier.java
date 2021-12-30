package oop.ex6.linesanalyzer.varsidentifier;


import oop.ex6.linesanalyzer.variables.Variable;
import oop.ex6.main.Scope;



/**
 * this class take line and identify the variables in the line
 */
public class VariableLineIdentifier extends VariableIdentifier {

    // check if its global line or not
    private boolean isGlobalLine;

    // map of variables
    private Scope currentScope;


    /**
     * this is the main constructor of the class
     *
     * @param global variable to check if its global
     * @param line   the line to check
     * @param currentScope the scope of the line
     */
    public VariableLineIdentifier(boolean global, String line,Scope currentScope) {
        this.line=line;
        this.isGlobalLine = global;
        this.line = line;
        this.currentScope=currentScope;
    }


    @Override
    public void identify() throws VarsExceptions {

        // check final
        identifyFinal();

        // check type and declare
        boolean checkType = identifyType();
        String[] varDec = line.split(SPLIT_OF_VARIABLES);

        // if exist type - add the variable
        if (checkType) {
            for (String varLine : varDec) {
                String name = varLine.split(REGEX_OF_SPLIT_WORDS, 1)[0];
                AddVar(name);
            }
        }

        //assign
        for (String varAndValue : varDec) {
            assignVars(varAndValue);
        }

    }



    // this method assign values to vars
    private void assignVars(String nameAndValue) throws VarsExceptions {
        String[] parts = nameAndValue.split(ASSIGNMENT);
        switch (parts.length) {

            // there is no assigment
            case 1:
                if (nameAndValue.contains(ASSIGN_VALUE)){
                    throw new MismatchAssignmentException();
                }

                // final must be assigned
                if (CurrentsAreFinal) {
                    throw new MismatchAssignmentException();
                }

                // there is no type
                else if(CurrentsType==null){
                    throw new IllegalTypeException();
                }
                break;
            case 2:
                String value = parts[1];
                Variable variable = currentScope.getVariable(parts[0]);

                // variable not exist
                if(variable==null){
                    throw  new MismatchAssignmentException();
                }
                Variable valueVariable = currentScope.getVariable(value);


                // final can't by assigned later
                if (variable.getFinal() && !CurrentsAreFinal) {
                    throw new MismatchAssignmentException();
                }

                // assign
                else if (valueVariable!=null) {//var exists
                    if (!valueVariable.isAssigned()) {
                        throw new MismatchAssignmentException();
                    } else {
                        if (!variable.getType().isTypeCompatible(valueVariable.getType())) {
                            throw new MismatchAssignmentException();
                        }
                    }
                } else {
                    variable.checkValue(value);
                    variable.assign();
                }
        }

    }

    @Override
    protected void AddVar(String name) throws VarsExceptions {
        String varName =identifyVarName(name);

        // exist name
        if (isGlobalLine){
            if (currentScope.getGlobalScope().containsKey(name)) {
                throw new VarExistException();
            }
        }
        else {
            if (currentScope.lastScopeContains(varName)) {
                throw new VarExistException();
            }
        }
        if (CurrentsType==null){
            throw new IllegalTypeException();
        }
        Variable variable = new Variable(varName, CurrentsType, isGlobalLine, CurrentsAreFinal);
        currentScope.add(variable);
        }
    }




