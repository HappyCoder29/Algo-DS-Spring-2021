package edu.northeastern.ashish;

public class Node <T> implements  Comparable<T>{

    public T data;
    public Node next;

    public Node(T data){
        this.data = data;
        this.next = null;
    }

    @Override
    public int compareTo(T other) {
        if(this.compareTo(other) > 0)
            return 1;
        else if(this.compareTo(other) == 0)
            return 0;
        else
            return -1;
    }
}
