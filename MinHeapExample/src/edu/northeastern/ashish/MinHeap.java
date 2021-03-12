package edu.northeastern.ashish;

import java.util.LinkedList;
import java.util.List;

public class MinHeap {

    private List<Integer> list;
    private int heapSize;

    public MinHeap(){
        list = new LinkedList<>();
        heapSize = 0;
    }

    public boolean isEmpty(){
        return  heapSize == 0;
    }

    public int peek(){
        if( isEmpty() ){
            return Integer.MAX_VALUE;
        }
        return  list.get(0);
    }

    private int getLeftChild(int n){
        return  2*n + 1;
    }
    private int getRightChild(int n){
        return  2*n + 2;
    }
    private int getParent(int n){
        return  (n-1)/2;
    }

    public void insert(int value){
        list.add(value);
        heapSize ++;
        siftUp(heapSize -1);
    }

    private void siftUp(int index){
        if( index == 0 ){
            return;
        }

        int parent = getParent(index);

        if( list.get(parent) >  list.get(index)){
            int temp = list.get(parent);
            list.set(parent, list.get(index));
            list.set(index, temp);
            siftUp(parent);
        }
    }

    public void delete(){
        if( isEmpty() ){
            return;
        }
        // Set the last value to the root of the heap
        list.set(0, list.get(heapSize -1));
        list.remove(heapSize -1) ;
        heapSize --;

        siftDown(0);
    }

    private void siftDown(int index){
        int leftChild = getLeftChild(index);
        int rightChild = getRightChild(index);

        int minIndex;
        if(rightChild >= heapSize){
            // if my left child exists
            if(leftChild >= heapSize){
                return;
            }else{
                minIndex = leftChild;
            }
        }else{
            // Where Left as well as right child exist
            if(list.get(leftChild) < list.get(rightChild)){
                minIndex = leftChild;
            }else{
                minIndex = rightChild;
            }
        }

        // At this point either we have returned or we have one minChild
        if(list.get(index) > list.get(minIndex)){
            int temp = list.get(index);
            list.set(index, list.get(minIndex));
            list.set(minIndex, temp);
            siftDown(minIndex);
        }
    }// End of siftDown

}
