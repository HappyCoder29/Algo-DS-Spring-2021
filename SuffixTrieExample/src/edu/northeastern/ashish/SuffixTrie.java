package edu.northeastern.ashish;

import java.util.Locale;

public class SuffixTrie {
    Node root;
    public SuffixTrie(){
        root = new Node('\0');
    }

    public void addString(String str){
        str += "$";

        for(int i = str.length() -1 ; i >= 0; i --){
            //str.substring(i);
           // System.out.println(str.substring(i));
            insert(str.substring(i));
        }
    }

    private void insert(String str){
        char[] arr = str.toLowerCase().toCharArray();
        Node current = root;
        for(int i = 0 ; i < arr.length; i ++){
            Node child = findChild(current, arr[i]);
            if(child == null){
                child = new Node(arr[i]);
                current.children.add(child);
            }
            current = child;
            if(i == arr.length -1){
                child.isTerminalCharacter = true;
            }
        }
    }

    private Node findChild(Node current, char ch ) {
        for (Node child : current.children) {
            if(child.ch == ch){
                return child;
            }
        }
        return  null;
    }

    public boolean isSubstring(String subStr){
        Node current = root;
        for(int i = 0 ; i < subStr.length(); i ++){
            Node child = findChild(current, subStr.charAt(i));
            if(child == null){
                return false;
            }
            current = child;
        }
        return true;
    }

    public int findNumOfTimesRepeated(String subStr){
        Node current = root;
        for(int i = 0 ; i < subStr.length(); i ++){
            Node child = findChild(current, subStr.charAt(i));
            if(child == null){
                return 0;
            }
            current = child;
        }
        return  findAllTerminal(current);
    }

    private int findAllTerminal(Node node){
        int result = node.isTerminalCharacter ? 1: 0;

        for (Node child : node.children) {
            result += findAllTerminal(child);
        }
        return result;
    }
}
