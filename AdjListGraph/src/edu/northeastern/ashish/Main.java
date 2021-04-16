package edu.northeastern.ashish;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.initializeDijkstra();

        graph.dijkstraShortestPath("1");

//        graph.initialize();
//
//        Graph clone = cloneGraph(graph);
//        System.out.println();
       // graph.topologicalSort();


        //System.out.println( graph.isCyclicDisJointSet() );



       // graph.breadthFirstSearch("A");
        //System.out.println( graph.isReachable("A", "D") );
        //graph.printAllPaths("A", "D");

     //   System.out.println(graph.isHamiltonian());

//        DisjointSet set = new DisjointSet();
//        Node node1 = new Node("1");
//        Node node2 = new Node("2");
//        Node node3 = new Node("3");
//        Node node4 = new Node("4");
//        Node node5 = new Node("5");
//
//        set.addNode(node1);
//        set.addNode(node2);
//        set.addNode(node3);
//        set.addNode(node4);
//        set.addNode(node5);
//
//
//        set.union(node1, node3);
//        set.union(node2, node4);
//        set.union(node3, node4);


        System.out.println("");
    }

    public static  Graph cloneGraph (Graph graphOrig){
        HashMap<String, Node> nodes = new HashMap<>();

        for ( Map.Entry<String, Node> entry : graphOrig.nodes.entrySet() ) {
            Node nodeOrig = entry.getValue();
            Node nodeClone = new Node(nodeOrig.name);

            nodes.put(nodeClone.name, nodeClone );
        }

        for ( Map.Entry<String, Node> entry : graphOrig.nodes.entrySet() ) {
            Node nodeOrig = entry.getValue();
            Node nodeClone = nodes.get(nodeOrig.name);

            for (Edge edge : nodeOrig.listEdges) {
                nodeClone.addEdge(edge.endNode, 1);
            }
        }

        Graph graphClone = new Graph();
        graphClone.nodes = nodes;

        return  graphClone;

    }
}
