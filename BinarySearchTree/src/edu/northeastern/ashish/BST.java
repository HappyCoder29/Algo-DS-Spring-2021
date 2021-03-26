package edu.northeastern.ashish;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BST {
    Node root;

    public BST(){
        //createBinaryTree();
    }

    private void createBinaryTree(){
        root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
    }

    private void createTree(){
        root = new Node(8);

        root.left = new Node(3);
        root.right = new Node(10);

        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.right = new Node(14);

        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        root.right.right.left = new Node(13);
    }


    public void preOrder(){
        preOrder(root);
        System.out.println();
    }
    private void preOrder(Node node){
        if(node != null){
            System.out.printf(node.data + ", ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    public void inOrder(){
        inOrder(root);
        System.out.println();
    }
    private void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.printf(node.data + ", ");
            inOrder(node.right);
        }
    }

    public void postOrder(){
        postOrder(root);
        System.out.println();
    }
    private void postOrder(Node node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.printf(node.data + ", ");
        }
    }


    public void reverseInOrder(){
        reverseInOrder(root);
        System.out.println();
    }
    private void reverseInOrder(Node node){
        if(node != null){
            reverseInOrder(node.right);
            System.out.printf(node.data + ", ");
            reverseInOrder(node.left);
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        if(node == null)
            return 0;
        return  size(node.left) + 1 + size(node.right);
    }

    public void levelOrder(){
        if(root == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while(queue.size() > 0){
            Node node = (Node) queue.remove();
            if(node != null){
                System.out.printf(node.data + ", " );
                if(node.left != null){
                    queue.add(  node.left );
                }
                if(node.right != null){
                    queue.add(  node.right );
                }
            }else{
                System.out.println("");
                if(queue.size() == 0 ){ break;}
                queue.add(null);
            }
        }
    }// end of level order


    public void insert(int data){
        Node node = new Node(data);
        if(root == null){
            root = node;
            return;
        }
        Node curr = root;
        Node parent = root;

        while(curr != null){
            parent = curr;
            if(curr.data < data){
                curr = curr.right;
            }else{
                curr = curr.left;
            }
        }
        if(parent.data < data){
            parent.right = node;
        }else{
            parent.left = node;
        }
    }


    public void inOrderExample(){
        BoxInt count = new BoxInt();
        inOrderExample(root, count);
        System.out.println("Count = " + count.value);

        System.out.println();
    }
    private void inOrderExample(Node node, BoxInt count ){
        if(node != null){
            inOrderExample(node.left, count);
            count.value ++;
            System.out.println("Count =" + count.value);
            inOrderExample(node.right, count );
        }
    }

    public Integer[] storeValuesInArr(){
        int size = size();
        Integer[] arr = new Integer[size];
        BoxInt ptr = new BoxInt();
        storeValuesInArr(root, arr, ptr);

        return  arr;
    }

    private void storeValuesInArr(Node node, Integer[] arr, BoxInt ptr){
        if(node != null){
            storeValuesInArr(node.left, arr, ptr);

            arr[ptr.value] = node.data;
            ptr.value ++;
            storeValuesInArr(node.right, arr, ptr);
        }
    }


    public void convertBinaryTreeToBST(){
        if( root == null){
            return;
        }
        int size = size();
        Integer[] arr = storeValuesInArr();

        Arrays.sort(arr);
        BoxInt ptr = new BoxInt();
        arrToBST(root, arr, ptr);

    }

    private void arrToBST(Node node, Integer[] arr, BoxInt ptr){

        if(node != null){
            arrToBST(node.left, arr, ptr);
            node.data = arr[ptr.value];
            ptr.value ++;
            arrToBST(node.right, arr, ptr);
        }
    }


    public void findKthSmallest(int k){
        if(root == null || k < 1){
            return;
        }

        BoxInt ptr = new BoxInt();
        findKthSmallest(root, k , ptr);

    }

    private void findKthSmallest(Node node, int k , BoxInt ptr){
        if(node != null){
            findKthSmallest(node.left, k, ptr);

            ptr.value ++;
            if(ptr.value == k){
                System.out.println("Kth smallest Value = " + node.data);
                return;
            }


            findKthSmallest(node.right, k, ptr);
        }
    }


    public void findKthLargest(int k){
        if(root == null || k < 1){
            return;
        }

        BoxInt ptr = new BoxInt();
        findKthLargest(root, k , ptr);

    }

    private void findKthLargest(Node node, int k , BoxInt ptr){
        if(node != null){
            findKthLargest(node.right, k, ptr);

            ptr.value ++;
            if(ptr.value == k){
                System.out.println("Kth smallest Value = " + node.data);
                return;
            }


            findKthLargest(node.left, k, ptr);
        }
    }



}
