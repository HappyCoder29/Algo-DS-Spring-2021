package edu.northeastern.ashish;

import java.util.LinkedList;
import java.util.List;

public class Node {
    public char ch;
    public boolean isTerminalCharacter;
    public List<Node> children;

    public Node(char ch){
        this.ch = ch;
        this.isTerminalCharacter = false;
        this.children = new LinkedList<>();
    }
}
