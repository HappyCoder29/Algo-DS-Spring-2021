package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        heap.insert(3);
        heap.insert(-2);
        heap.insert(5);
        heap.insert(8);
        heap.insert(-1);
        heap.insert(4);
        heap.insert(9);

        System.out.println(heap.peek());
        heap.delete();
        heap.delete();
        heap.delete();

        System.out.println(heap.peek());

    }
}
