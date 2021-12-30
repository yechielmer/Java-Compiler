package oop.ex6.blocktreeexctractor;

import oop.ex6.linesanalyzer.variables.Variable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * this is a node in the code block tree
 */
public class CodeBlockNode{
    /** the parent Node*/
    private CodeBlockNode parent;
    /** list of children*/
    private List<CodeBlockNode> children;
    /** the start of this block*/
    private int start;
    /** the end of this block*/
    private int end;
    /** map ov variables in the current scope*/
    private HashMap<String, Variable> localVarList;

    /**
     * constructor for root node
     * @param start the starting index of the block
     * @param end the end index of the block
     */
    public CodeBlockNode(int start, int end){
        this.parent = null;
        this.children = new LinkedList<>();
        this.start=start;
        this.end=end;
        this.localVarList = new HashMap<>();
    }

    /**
     * constructor for child node
     * @param parent parent of the current node
     * @param start the starting index of the block
     * @param end the end index of the block
     */
    public CodeBlockNode(CodeBlockNode parent,int start, int end){
        this.parent = parent;
        this.children = new LinkedList<>();
        this.start=start;
        this.end=end;
        this.localVarList = new HashMap<>();
    }

    /**
     * this method adds a child to the node
     * @param start for the child node
     * @param end closing parentheses for the child node
     * @return the new child
     */
    public CodeBlockNode addChild(int start, int end){
        CodeBlockNode child = new CodeBlockNode(this,start,end);
        children.add(child);
        return child;
    }

    /**
     * this method sets the parent of the node
     * @param parent parent to set
     */
    public void setParent(CodeBlockNode parent) {
        this.parent = parent;
    }

    /**
     *
     * @return the starting index of the code block
     */
    public int getStart() {
        return start;
    }

    /**
     *
     * @return the end index of this code block
     */
    public int getEnd() {
        return end;
    }

    /**
     * this method returns the nodes children
     * @return the node children
     */
    public List<CodeBlockNode> getChildren() {
        return children;
    }



    /**
     * this method returns the node's parent
     * @return the node's parent
     */
    public CodeBlockNode getParent() {
        return parent;
    }

    /**
     *
     * @return all the lines that are in the node level of this block
     */
    public boolean[] getCodeLineIndexes(){
        int length = this.end-this.start+1;
        // create array
        boolean[] CheckList=new boolean[length];
        Arrays.fill(CheckList, true);

        // check if exist parentheses
        for (CodeBlockNode node :children){
            for (int j=node.getStart()-start; j<=node.getEnd()-start; j++){
                CheckList[j]=false;
            }
        }
        return CheckList;
    }



    /**
     * this method shrinks the block by the removing the first and last lines
     */
    public void removeFirstAndLast() {
        start++;
        end--;
    }

    /**
     * this method returns the child that are in the given index
     * @param index index of child
     * @return child in index
     */
    public CodeBlockNode getChildInIndex(int index){
        for (CodeBlockNode child :children){
            if(child.start<=index && index<=child.end){
                return child;
            }
        }
        return null;
    }

    /**
     * this method removes lines for a method declartion block
     * @param returnIndex the index of the return stament
     */
    public void removeMethodDec(int returnIndex) {
        start++;
        end=returnIndex-1;
    }
}
