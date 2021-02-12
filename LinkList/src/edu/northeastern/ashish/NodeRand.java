package edu.northeastern.ashish;

public class NodeRand <T> {

    public T data;
    public NodeRand next;
    public NodeRand rand;

    public NodeRand(T data){
        this.data = data;
        this.next = null;
        this.rand = null;

    }
}
