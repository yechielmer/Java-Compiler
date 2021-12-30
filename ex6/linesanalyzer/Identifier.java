package oop.ex6.linesanalyzer;

import oop.ex6.linesanalyzer.methodcall.MismatchFunctionException;
import oop.ex6.linesanalyzer.varsidentifier.VarsExceptions;
import oop.ex6.methodanalyzer.MethodExceptions;
import oop.ex6.methodanalyzer.NotEnoughArgsException;
import oop.ex6.methodanalyzer.IllegalMethodDeclaration;

/**
 * this is the main interface of identify var or arguments
 */
public interface Identifier {
    /**
     * this method identify the current line
     * @throws VarsExceptions when encountering var related problams
     * @throws LineExceptions when encountering problem reading a line
     * @throws MethodExceptions when encountering problems related to methods
     */
    void identify() throws VarsExceptions, LineExceptions, MethodExceptions;
}
