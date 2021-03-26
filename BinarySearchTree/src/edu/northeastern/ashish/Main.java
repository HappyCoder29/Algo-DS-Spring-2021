package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
        BST bst = new BST();
//        bst.insert(8);
//        bst.insert(3);
//        bst.insert(10);
//        bst.insert(1);
//        bst.insert(6);
//        bst.insert(14);
//        bst.insert(4);
//        bst.insert(7);
//        bst.insert(13);

//        bst.preOrder();
//        bst.inOrder();
//        bst.postOrder();
//        bst.reverseInOrder();
//
//        bst.levelOrder();
//        bst.inOrderExample();

     //   Integer[] arr =  bst.storeValuesInArr();

//        for (Integer i : arr) {
//            System.out.printf(i + ",");
//        }
//        System.out.println();

//        Integer[] arr1 = {1,2,3,4,5,6,7,8};
//
//        Node node = createBalancedBSTFromSortedArray(arr1);
//
//        System.out.println();

        bst.convertBinaryTreeToBST();
        bst.inOrder();

    }


    static Node createBalancedBSTFromSortedArray(Integer[] arr){
        if(arr.length == 0 ){
            return null;
        }

        Node node = createBalancedBSTFromSortedArray(arr, 0,  arr.length -1);
        return node;
    }

    static Node createBalancedBSTFromSortedArray(Integer[] arr, int start, int end){
        if(start > end){
            return null;
        }

        int mid = (start + end)/2;

        Node node = new Node(arr[mid]);

        node.left = createBalancedBSTFromSortedArray(arr, start, mid -1);
        node.right = createBalancedBSTFromSortedArray(arr, mid +1, end);

        return node;

    }
}
