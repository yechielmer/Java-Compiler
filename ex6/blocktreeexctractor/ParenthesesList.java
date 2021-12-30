package oop.ex6.blocktreeexctractor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * this is a parentheses list, you can push parentheses into the list, and generate a parentheses tree
 */
public class ParenthesesList {
    /** stack for holding all parentheses*/
    private final List<Parentheses> parenthesesList;
    /** opened parentheses*/
    private int opened;

    /**
     * constructor for a new parentheses list
     */
    public ParenthesesList(){
        parenthesesList = new ArrayList<>();
        opened = 0;
    }

    /**
     * adds a new parentheses to the list
     * @param parentheses parentheses to add
     * @throws ParenthesesMismatchException if the given parentheses can't be a part of a valid code file,
     * given this exception you should not push more items!
     */
    public void push(Parentheses parentheses) throws ParenthesesMismatchException {
        if (parentheses.getType()==Parentheses.OPENER){
            opened++;
        }
        else {//closing parentheses
            if (opened<=0)
                throw new ParenthesesMismatchException();
            opened--;
        }
        parenthesesList.add(parentheses);
    }

    /**
     * this method generates a tree for the current list
     * @return parentheses tree
     * @throws ParenthesesMismatchException if parentheses structure is illegal
     */
    public CodeBlockNode createTree(int start,int end) throws ParenthesesMismatchException {
        if(parenthesesList.size()==0){//only global scope
            return null;
        }
        if (opened!=0)//can;t create tree if there is an unclosed parentheses
            throw new ParenthesesMismatchException();
        CodeBlockNode root = new CodeBlockNode(start,end);
        createTreeHelper(root,0,parenthesesList.size()-1);
        return root;
    }

    /**
     * this helper method build a tree from the given root
     * @param root current VALID root of the tree
     * @param start the starting index on the parentheses list to search for children
     * @param end the ending index on the parentheses list to search for children
     * @throws ParenthesesMismatchException if parentheses structure is illegal
     */
    private void createTreeHelper(CodeBlockNode root, int start, int end)
            throws ParenthesesMismatchException {
        if(start>end){//base case
            return;
        }
        int currentStart = start;
        while (currentStart<=end) {//search for children on the given range
            if(parenthesesList.get(currentStart).getType()==Parentheses.OPENER) {
                int closure = findClosure(currentStart, end);//find respective closure
                CodeBlockNode child = root.addChild(parenthesesList.get(currentStart).getIndex(), parenthesesList.get(closure).getIndex());
                //child.removeFirstAndLast();//first anf last line already verified
                createTreeHelper(child, currentStart+1, closure-1);
                currentStart = closure + 1;
            }
            else {
                currentStart++;
            }
        }
    }

    /**
     * this method find the closure of the given parentheses opener
     * @param openerIndex index of the opener in the parentheses list
     * @param endIndex maximal index to search
     * @return the index of the respective closure
     * @throws ParenthesesMismatchException if no closure found, the parentheses structure is illegal
     */
    private int findClosure(int openerIndex, int endIndex) throws ParenthesesMismatchException {
        LinkedList<Parentheses> stack = new LinkedList<>();
        for (int i = openerIndex;i<=endIndex;i++){
            if(parenthesesList.get(i).getType() == Parentheses.OPENER){
                stack.addFirst(parenthesesList.get(i));
            }
            else if (parenthesesList.get(i).getType()==Parentheses.CLOSURE){
                stack.pop();
                if(stack.size()==0){
                    return i;
                    }
                }
            }
        throw new ParenthesesMismatchException();
    }



}
