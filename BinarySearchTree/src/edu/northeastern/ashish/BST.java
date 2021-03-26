package edu.northeastern.ashish;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    Node root;

    public BST(){
        //createTree();
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


}
