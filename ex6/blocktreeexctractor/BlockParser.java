package oop.ex6.blocktreeexctractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this class handles translating code lines into a code block tree
 */
public class BlockParser {
    // sign of opening parentheses
    private static final String PARENTHESES_OPENER = "{";
    // sign of closing parentheses
    private static final String PARENTHESES_CLOSURE = "}";
    /** all lines in the file*/

    private ArrayList<String> codeLines;
    /** Parentheses list for creating tree*/

    private ParenthesesList parenthesesList;
    /** Parentheses Node for storing the tree*/
    private CodeBlockNode tree;

    /**
     * this method builds a new block parser
     * @param filename file that containes the block
     * @throws IOException if encountered exception when reading file
     */
    public BlockParser(String filename) throws IOException {
        this.codeLines = new ArrayList<>();
        getCommandsFromFile(filename);
        this.parenthesesList = new ParenthesesList();
    }

    /**
     * this method extract the lines file to a list
     * @param filePath path to file
     * @throws IOException when problems reading from file
     */
    private void getCommandsFromFile(String filePath) throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();//getting first line outside the loop
        while (line != null) {
            codeLines.add(line);
            line = reader.readLine();
        }
        reader.close();
    }

    /**
     * this method genrates a full paretheses tree for the file
     * @throws ParenthesesMismatchException if illegal parentheses found
     */
    public void generateTree() throws ParenthesesMismatchException {
        for (int i = 0;i<codeLines.size();i++){
            String currentLine = codeLines.get(i).strip();
            if(currentLine.endsWith(PARENTHESES_OPENER)){
                parenthesesList.push(new Parentheses(Parentheses.OPENER,i));
            }
            else if(currentLine.startsWith(PARENTHESES_CLOSURE)&&currentLine.length()==1){
                parenthesesList.push(new Parentheses(Parentheses.CLOSURE,i));
            }
        }
        this.tree= parenthesesList.createTree(0,codeLines.size()-1);
        if(tree ==null){
            tree = new CodeBlockNode(0,codeLines.size()-1);
        }

    }

    /**
     * this method returns the parentheses tree, should be called after create tree
     * @return parentheses tree
     */
    public CodeBlockNode getTree() {
        return tree;
    }

    /**
     *
     * @return the code lines in a file
     */
    public ArrayList<String> getCodeLines() {
        return codeLines;
    }
}
