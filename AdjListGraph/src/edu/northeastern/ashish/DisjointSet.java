package edu.northeastern.ashish;

import java.util.HashMap;

public class DisjointSet {

    public HashMap<String, Node> nodes;

    public  DisjointSet(){
        nodes = new HashMap<>();
    }

    public void addNode(Node node){
        if(nodes.containsKey(node.name)){
            return;
        }
        nodes.put(node.name, node);
    }

    private Node findParent(Node node){

        Node parent = node.parent;
        if(parent == node){
            return  node;
        }

        node.parent = findParent(node.parent);

        return  node.parent;

    }

    public void union(Node node1, Node node2){
        if(! nodes.containsKey(node1.name) || ! nodes.containsKey(node2.name)){
            return;
        }

        Node parent1 = findParent(node1);
        Node parent2 = findParent(node2);

        if(parent1 == parent2){
            return;
        }

        if(parent1.rank >= parent2.rank){

            if(parent1.rank == parent2.rank){
                parent1.rank ++;
            }
            parent2.parent = parent1;
        }else{
            parent1.parent = parent2;
        }

    }


}
