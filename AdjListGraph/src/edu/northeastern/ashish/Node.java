package edu.northeastern.ashish;



import java.util.LinkedList;

public class Node {

    public String name;
    public Boolean visited;
    public LinkedList<Edge> listEdges;

    public Node (String name){
        this.name =  name.toUpperCase();
        this.visited = false;
        this.listEdges = new LinkedList<Edge>();
    }

    public void addEdge(String endNode, int weight){
        endNode = endNode.toUpperCase();

        for (Edge edge : listEdges) {
            if( edge.endNode == endNode ){
                return;
            }
        }

        Edge edge = new Edge(name, endNode, weight);
        listEdges.add(edge);
    }

    public void deleteEdge(String endNode){
        endNode = endNode.toUpperCase();

        for (Edge edge : listEdges) {
            if( edge.endNode == endNode){
                listEdges.remove(edge);
                return;
            }
        }
    }
    public void  setVisited(Boolean visited){ this.visited = visited; }

}


