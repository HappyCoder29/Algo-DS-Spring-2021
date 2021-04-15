package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
       // graph.breadthFirstSearch("A");
        //System.out.println( graph.isReachable("A", "D") );
        //graph.printAllPaths("A", "D");

        System.out.println(graph.isHamiltonian());

        System.out.println("");
    }
}
