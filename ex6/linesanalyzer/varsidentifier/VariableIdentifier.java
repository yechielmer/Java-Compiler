package oop.ex6.linesanalyzer.varsidentifier;

import oop.ex6.linesanalyzer.Identifier;
import oop.ex6.linesanalyzer.variables.VariableType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * abstract class of identify variables
 */
public abstract class VariableIdentifier implements Identifier {
    // the splitter for words
    public static final String REGEX_OF_SPLIT_WORDS = " ";
    // the splitter of the variables
    protected static final String SPLIT_OF_VARIABLES = "\\s*,\\s*";

    // regex of opening Word
    protected final String NAME_REGEX = "(^[a-zA-Z][\\w\\d_]*)|(^_[\\w\\d_]+)";

    // string for assignment
    protected static final String ASSIGNMENT = "\\s*=\\s*";

    // string for assignment
    protected static final String ASSIGN_VALUE = "=";
    // check if final
    protected final String FINAL = "final ";
    /**
     * the type of the current variable
     */
    protected String CurrentsType;

    /**
     * line to identify
     */
    protected String line;

    /**are the variables final*/
    protected boolean CurrentsAreFinal;

    /**
     * this class identify a variable declaration and or assignment
     */
    public VariableIdentifier() {
    }

    /**
     * this method identify the given line
     * @throws VarsExceptions the exceptions of vars
     */
    public abstract void identify() throws VarsExceptions;

    /**
     * this method checks if the line is final var declaration
     * @throws FinalDecMisplacedException if final is placed in incorrect scope
     */
    protected void identifyFinal() throws FinalDecMisplacedException {
        if (line.startsWith(FINAL)) {
            CurrentsAreFinal = true;
            line = line.substring(FINAL.length() );
        }
    }


    /**
     * this method checks id a var is of legal type
     * @return true if exist type in name, false if not
     * @throws VarsExceptions exception in case there is not type and its global
     */
    protected boolean identifyType() throws VarsExceptions {
        String[] lineParts = line.split(REGEX_OF_SPLIT_WORDS, 2);

        // check if there is type
        boolean checkType =VariableType.checkLegalType(lineParts[0]);
        if (!checkType) {
            // not type and its final
            if (CurrentsAreFinal) {
                throw new IllegalTypeException();
            } else return false;
        } else {

            // too short line
            if (lineParts.length == 1) {
                throw new TooShortVarException();
            }
            line = lineParts[1];
            CurrentsType = lineParts[0];
            return true;
        }
    }

    /**
     * this method identify the var name
     * @param var var name to check
     * @throws IllegalVarNameException
     */
    protected String identifyVarName(String var) throws IllegalVarNameException {
        String[] parts=var.split(ASSIGNMENT);
        Pattern p = Pattern.compile(NAME_REGEX);
        Matcher m = p.matcher(parts[0]);
        if (!m.matches()) {
            throw new IllegalVarNameException();
        }
        return parts[0];
    }

    /**
     * this method add an var to list of vars
     * @param name the name of the var to add
     * @throws VarsExceptions some exceptions of illegal vars
     */
    protected abstract void AddVar(String name) throws VarsExceptions;

}
