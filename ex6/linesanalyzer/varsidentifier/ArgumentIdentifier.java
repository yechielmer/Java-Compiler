package oop.ex6.linesanalyzer.varsidentifier;

import oop.ex6.linesanalyzer.AnalyzerUtils;
import oop.ex6.linesanalyzer.variables.Variable;
import oop.ex6.linesanalyzer.varsidentifier.VariableIdentifier;

import java.util.LinkedList;
import java.util.List;



/**
 * this class get arguments in an function, and check it
 */
public class ArgumentIdentifier extends VariableIdentifier {

    // the arguments to check
    private List<Variable> arguments;

    // regex for an whitespace
    private static final String REGEX_WHITESPACE = "\\s{2,}";

    // regex for an space
    private static final String SPACE = " ";

    // regex for an empty char
    private static final String EMPTY_CHAR = "";

    // regex for an whitespace in the end of the line
    private static final String WHITESPACE_END_OF_LINE = "\\s+$";

    // regex for an whitespace in the start of the line
    public static final String SPACES_START_OF_LINE = "^[\\s]+";
    /**
     * this class identify a variable declaration and or assignment
     */
    public ArgumentIdentifier() {
        this.arguments = new LinkedList<>();
    }

    /**
     * setter for the line field
     *
     * @param line line to check
     */
    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public void identify() throws VarsExceptions {
        if (line.length()==0){
            return;
        }
        line = AnalyzerUtils.cleanWhiteSpaces(line);
        // check final
        identifyFinal();
        // check type and declare
        if (!identifyType()){
            throw new IllegalTypeException();
        }
        String name = line.split(REGEX_OF_SPLIT_WORDS, 1)[0];

        if (CurrentsType!=null) {
            AddVar(name);
        }

    }

    @Override
    protected void AddVar(String name) throws VarsExceptions {
        String varName =identifyVarName(name);
        for (Variable var : arguments) {
            if (var.getName().equals(name)) {
                throw new VarExistException();
            }
        }
        Variable variable = new Variable(varName, CurrentsType, false, CurrentsAreFinal);
        variable.assign();//arguments are always assigned!
        arguments.add(variable);
    }

    /**
     * this method returns the genrated list
     *
     * @return argument list
     */
    public List<Variable> getArgumentList() {
        return arguments;
    }

   
}
