package edu.northeastern.ashish;

import java.util.*;

public class Graph {
    public HashMap<String, Node> nodes;
    public Node startNode;
    public Graph(){
        nodes = new HashMap<>();
      //  initialize();
      //  initializeDAG();
    }

    public void initialize() {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        // Add edges
        A.addEdge("B", 1);
        B.addEdge("C", 1);
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

    private void initializeDAG(){
        Node node0 = new Node("0");
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");

        // Add edges
        node2.addEdge("3", 1);
        node3.addEdge("1", 1);
        node4.addEdge("0", 1);
        node4.addEdge("1", 1);
        node5.addEdge("2", 1);
        node5.addEdge("0", 1);

        nodes.put(node0.name, node0);
        nodes.put(node1.name, node1);
        nodes.put(node2.name, node2);
        nodes.put(node3.name, node3);
        nodes.put(node4.name, node4);
        nodes.put(node5.name, node5);

    }

    public void initializeDijkstra(){
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        Node node6 = new Node("6");

        // Add edges
        node1.addEdge("2", 7);
        node1.addEdge("3", 9);
        node1.addEdge("6", 14);

        node2.addEdge("1", 7);
        node2.addEdge("3", 10);
        node2.addEdge("4", 15);

        node3.addEdge("1", 9);
        node3.addEdge("2", 10);
        node3.addEdge("4", 11);
        node3.addEdge("6", 2);

        node4.addEdge("2", 15);
        node4.addEdge("3", 11);
        node4.addEdge("5", 6);

        node5.addEdge("4", 6);
        node5.addEdge("6", 9);

        node6.addEdge("1", 14);
        node6.addEdge("3", 2);
        node6.addEdge("5", 9);

        nodes.put(node1.name, node1);
        nodes.put(node2.name, node2);
        nodes.put(node3.name, node3);
        nodes.put(node4.name, node4);
        nodes.put(node5.name, node5);
        nodes.put(node6.name, node6);

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

    public boolean containsCycle(){

        for (Map.Entry<String, Node> entry : nodes.entrySet()) {
            System.out.println("Starting with " + entry.getKey());
            Stack<Node> stack = new Stack<>();
            if( containsCycle( entry.getValue(), stack )  ){
                return true;
            }
        }

        return false;
    }

    private boolean containsCycle(Node node, Stack<Node> stack){

        if( stack.contains(node) ){
            return  true;
        }

        stack.push(node);

        for (Edge edge : node.listEdges) {
          //  System.out.println("Node name  = " + node.name);
         //   System.out.println("End name  = " + edge.endNode);

            if( containsCycle(nodes.get(edge.endNode), stack ) ){
                return true;
            }

        }
        stack.pop();
        return false;
    }

    public boolean isHamiltonian(){
        RefStore<List<String>> result = new RefStore<>(new LinkedList<>());
        boolean bHamiltonian = isHamiltonian(result);

        if(bHamiltonian){
            for (String str : result.value) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }
        return bHamiltonian;

    }

    private boolean isHamiltonian(RefStore<List<String>> result ){
        Map.Entry<String, Node> entry = nodes.entrySet().iterator().next();

        String startNode = entry.getKey();
        HashSet<String> visited = new HashSet<>();
        return  isHamiltonian(startNode, startNode, result, visited);

    }

    private boolean isHamiltonian(String startNode, String currentNode, RefStore<List<String>> result
            , HashSet<String> visited) {

        visited.add(currentNode);
        result.value.add(currentNode);

        LinkedList<Edge> edges = nodes.get(currentNode).listEdges;

        for(int i = 0 ; i < edges.size(); i ++){

            if(startNode == edges.get(i).endNode && nodes.size() == result.value.size()){
                result.value.add(startNode);
                return true;
            }

            if ( !visited.contains(edges.get(i).endNode) ){
                boolean isHamil = isHamiltonian(startNode, edges.get(i).endNode, result, visited);
                if( isHamil){
                    return true;
                }
            }

        }

        result.value.remove(result.value.size() -1);
        visited.remove(currentNode);
        return false;

    }

    public boolean isCyclicDisJointSet(){

        // Add All the nodes inside the disjoint set
        DisjointSet set = new DisjointSet();
        for (Map.Entry<String, Node> entry : nodes.entrySet()) {
            entry.getValue().parent = entry.getValue();
            entry.getValue().rank = 0;
            set.addNode(entry.getValue());
        }

        for (Map.Entry<String, Node> entry : nodes.entrySet()){
            Node startNode = entry.getValue();

            for (Edge edge : startNode.listEdges) {
                Node endNode = nodes.get(edge.endNode);
                Node parent1 = startNode.parent;
                Node parent2 = endNode.parent;
                // There is a back cycle so we return true
                if(parent1 == parent2){
                    return true;
                }
                set.union(startNode, endNode);
            }
        }

        return false;

    }


    public void topologicalSort(){
        Stack<String> stack = new Stack<>();

        HashSet<String> visited = new HashSet<>();

        for (Map.Entry<String, Node> entry : nodes.entrySet()){
            Node node = entry.getValue();

            if(!visited.contains(node.name)){
                topologicalSort(node.name, visited, stack);
            }
        }

        // print the stack
        while(stack.size() != 0){
            System.out.printf(stack.pop() +  " ");
        }
        System.out.println();

    }

    private void topologicalSort( String nodeName,  HashSet<String> visited, Stack<String> stack){
        // Add node in visited hashset
        visited.add(nodeName);

        // list of edges and if we have not visited the node we will recurse for that node
        List<Edge> edges = nodes.get(nodeName).listEdges;

        for(int i = 0 ; i < edges.size(); i ++){
            if( !visited.contains(edges.get(i).endNode)){
                topologicalSort(edges.get(i).endNode, visited, stack);
            }
        }
        // At this point al the neighbours of nodeName and the nodes below that are added in the stack
        // Now We can add the current node i.e. nodeName
        stack.push(nodeName);
    }


    public void dijkstraShortestPath(String startNode){
        if(!nodes.containsKey(startNode)){
            return;
        }
        resetVisited();

        Node currentNode = nodes.get(startNode);
        currentNode.distance = 0;
        currentNode.cameFrom = startNode;

        while(currentNode != null ){

            for (Edge edge : currentNode.listEdges) {
                Node node = nodes.get(edge.endNode);
                if(currentNode.distance + edge.weight < node.distance){
                    node.distance = currentNode.distance + edge.weight;
                    node.cameFrom = currentNode.name;
                }
            }
            currentNode.visited = true;

            // Find the next node from unvisited which is smallest distance
            currentNode = null;

            for (Map.Entry<String, Node> entry : nodes.entrySet()){
                Node node = entry.getValue();
                if(node.visited == true){
                    continue;
                }
                if(currentNode == null){
                    currentNode = node;
                }else{
                    if(currentNode.distance > node.distance){
                        currentNode = node;
                    }
                }
            }
        }

        System.out.println("At this point we should have the shortest distances");

        for (Map.Entry<String, Node> entry : nodes.entrySet()){
            Node node = entry.getValue();
            System.out.println(node.name + " " +  node.distance + " ");
        }
        System.out.println("At this point we should have the shortest distances");

    }






}
