package edu.northeastern.ashish;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class Graph {
    public HashMap<String, Node> nodes;
    public Node startNode;
    public Graph(){
        nodes = new HashMap<>();
        initialize();
    }

    private void initialize() {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        // Add edges
        A.addEdge("B", 1);
        B.addEdge("C", 1);
        B.addEdge("D", 1);
        C.addEdge("E", 1);
        E.addEdge("F", 1);
        E.addEdge("D", 1);
        D.addEdge("B", 1);

        // Add Nodes in the HashMap (Graph)

        nodes.put(A.name, A);
        nodes.put(B.name, B);
        nodes.put(C.name, C);
        nodes.put(D.name, D);
        nodes.put(E.name, E);
        nodes.put(F.name, F);
    }

    public void addNode(Node node){
        if(nodes.containsKey(node.name)){
            return;
        }
        nodes.put(node.name, node);
    }

    public void removeNode(Node node){
        if(nodes.containsKey(node.name)){
            nodes.remove(node);
        }
    }

    public void resetVisited(){
        nodes.forEach( (k,v)-> {
            v.setVisited(false);
        });
    }

    public void breadthFirstSearch(String startNode){
        startNode = startNode.toUpperCase();
        if( !nodes.containsKey(startNode)){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes.get(startNode));
        queue.add(null);

        while (queue.size() != 0){
            Node node = queue.remove();
            if(node != null ){
                // Node is already visited
                if(node.visited == true){
                    continue;
                }
                System.out.print(node.name + " ");
                node.visited = true;
                LinkedList<String> neighbours = node.getNeighbours();
                for (String nodeStr : neighbours) {
                    if( nodes.get(nodeStr).visited == false){
                        queue.add(nodes.get(nodeStr));
                    }
                }
            }else{
                // The node was null
                System.out.println("");
                if(queue.size() == 0 ){
                    break;
                }
                queue.add(null);
            }
        }

    }





}
