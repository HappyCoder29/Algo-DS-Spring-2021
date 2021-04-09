package edu.northeastern.ashish;

import java.util.*;

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
        resetVisited();
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
                for (Edge edge : node.listEdges) {
                    if( nodes.get(edge.endNode).visited == false){
                        queue.add(nodes.get(edge.endNode));
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

    public void depthFirstSearch(String startNode){
        startNode = startNode.toUpperCase();
        if( !nodes.containsKey(startNode)){
            return;
        }

        resetVisited();

        Stack<Node> stack = new Stack<>();
        stack.push(nodes.get(startNode));

        while(stack.size() != 0 ){
            Node node = stack.pop();
            if(node.visited == true){
                continue;
            }
            System.out.print(node.name + " ");
            node.visited = true;
            for (Edge edge : node.listEdges) {
                if( nodes.get(edge.endNode).visited == false){
                    stack.push(nodes.get(edge.endNode));
                }
            }
        }
    }// end of depthFirstSearch


    public boolean isReachable(String startNode, String endNode){
        startNode = startNode.toUpperCase();
        endNode = endNode.toUpperCase();
        if( ! nodes.containsKey(startNode) || !nodes.containsKey(endNode)){
            return false;
        }

        resetVisited();
        Queue<Node> queue = new LinkedList<>();
        queue.add(nodes.get(startNode));
        queue.add(null);

        while(queue.size() != 0 ){
            Node node = queue.remove();
            if(node != null ){
                if(node.visited == true){
                    continue;
                }
                System.out.print(node.name + " ");
                node.visited = true;

                for (Edge edge : node.listEdges) {
                    if(nodes.get(edge.endNode).visited == false) {
                       if(edge.endNode == endNode){
                           System.out.println();
                           System.out.println(endNode);
                           return true;
                       }
                       queue.add(nodes.get(edge.endNode));
                   }
                }

            }else{
                // node was null
                System.out.println();
                if(queue.size() == 0 ){
                    break;
                }
                queue.add(null);
            }
        }

        return false;


    }


    public void printAllPaths(String source, String dest){
        source = source.toUpperCase();
        dest = dest.toUpperCase();

        if( !nodes.containsKey(source) || !nodes.containsKey(dest) ){
            return;
        }

        LinkedList<String> visited = new LinkedList<>();
        printAllPaths(visited, source, dest);

    }

    private void printAllPaths(LinkedList<String> visited, String current, String dest){
        if( visited.contains(current) ){
            return;
        }
        if(dest == current){
            for (String str : visited) {
                System.out.print(str + " -> ");
            }
            System.out.println(dest);
        }

        visited.add(current);

        Node node = nodes.get(current);
        for (Edge edge : node.listEdges) {
            if( ! visited.contains(edge.endNode) ){
                printAllPaths(visited, edge.endNode, dest);
            }
        }

        visited.remove(current);
    }




}
