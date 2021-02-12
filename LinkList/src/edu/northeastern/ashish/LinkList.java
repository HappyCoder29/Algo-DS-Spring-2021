package edu.northeastern.ashish;

import java.util.ArrayList;

public class LinkList <T> {
    public Node<T> head;
    public LinkList(){

    }

    public void addHead(T data){
        Node<T> node = new Node<>(data);
        node.next = head;
        head = node;
    }

    public void addTail(T data){
        Node<T> node = new Node<>(data);
        // If the list is empty
        if(head == null){
            head = node;
            return;
        }

        // ove temp node to last node
        Node<T> temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public Node<T> getTailNode(){
        if(head == null || head.next == null){
            return head;
        }
        Node<T> temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        return  temp;
    }

    public void printList(){
        Node<T>  temp = head;

        while(temp != null){
            System.out.printf(temp.data + "-> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    public int size(){
        int size = 0;
        Node<T>  temp = head;
        while(temp != null){
            temp = temp.next;
            size++;
        }
        return size;
    }

    /// Assuming there is no cycle
    public void reverse(){
        if(head == null || head.next == null){
            return;
        }
        Node<T> back = null;
        Node<T> mid = head;
        Node<T> front = head.next;

        while(front != null){
            mid.next = back;
            back = mid;
            mid = front;
            front = front.next;
        }
        mid.next = back;
        head = mid;
    }


    public Node<T> reverse(Node<T> node){
        if(node == null || node.next == null){
            return null;
        }
        Node<T> back = null;
        Node<T> mid = node;
        Node<T> front = node.next;

        while(front != null){
            mid.next = back;
            back = mid;
            mid = front;
            front = front.next;
        }
        mid.next = back;
        node = mid;
        return  node;
    }

    public Node<T> findKthFromEnd(int k){
        if(k < 0 || head == null){
            return null;
        }
        Node<T> front = head;
        Node<T> back = head;

        // Move the front by K times
        for(int i = 0 ; i < k ; i ++){
            if(front == null){
                return null;
            }
            front = front.next;
        }

        // keep moving front and back by one till front reaches null
        while(front != null){
            back = back.next;
            front = front.next;
        }

        return back;
    }


    public boolean isCyclic(){
        if(head == null || head.next == null){
            return false;
        }

        Node<T> back = head;
        Node<T> front = head;

        while(front != null){

            front = front.next;

            if(front == null){
                return false;
            }
            front = front.next;
            back = back.next;

            if(front == back){
                return true;
            }
        }
        return false;

    }

    public Node<T> findStartOfCycle(){
        if(head == null || head.next == null){
            return null;
        }

        Node<T> back = head;
        Node<T> front = head;

        while(front != null){

            front = front.next;

            if(front == null){
                return null;
            }
            front = front.next;
            back = back.next;

            // we know there is a cycle now we have to find the start of cycle
            if(front == back){
                break;
            }
        }
        if(front == null){
            return null;
        }

        // We definitely know there is a cycle
        // front and back are K steps away from the start of cycle
        // where k = dist from head to start of cycle

        // we move either front of back to head;
        back = head;

        // move both front and back by one
        while(front != back){
            front = front.next;
            back = back.next;
        }

        return front;
    }

    public Node<T> breakListInHalf(){
        if(head == null || head.next == null){
            return null;
        }

        Node front = head;
        Node back = head;

        while(front.next != null){
            front = front.next;
            if(front.next != null) {
                front = front.next;
                back = back.next;
            }
        }
        Node temp = back.next;
        back.next = null;

        return temp;

    }



    public boolean isPalindrome(){
        if(head == null || head.next == null){
            return true;
        }

        Node<T> secondHalf = breakListInHalf();
        secondHalf = reverse(secondHalf);

        Node temp1 = head;
        Node temp2 = secondHalf;
        boolean bPalindrome = true;

        while(temp1 != null && temp2 != null){
            if(temp1.data != temp2.data){
                bPalindrome = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        // Put the list back together
        secondHalf = reverse(secondHalf);
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = secondHalf;
        return  bPalindrome;
    }

    //https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public void removeNthFromEnd(int n){
        if(n <=0 || head == null){
            return;
        }

        int count = size();
        if(count == n){
            head = head.next;
            return;
        }

        Node<T> node = findKthFromEnd(n +1);
        if(node == null){
            return;
        }

        Node<T> deletedNode = node.next;

        node.next = node.next.next;

        deletedNode = null;
    }

    // https://leetcode.com/problems/reverse-nodes-in-k-group/
    public void reverseNodesInKGroup(int k){
        if(k <= 1 || head == null || head.next == null){
            return;
        }


    }

//    //https://leetcode.com/problems/split-linked-list-in-parts/
//    public ArrayList<Node<T>> splitListInParts(int k){
//        ArrayList<Node<T>> listNodes = new ArrayList<>();
//        if(k <= 0 || head == null || head.next == null){
//            listNodes.add(head);
//            return  listNodes;
//        }
//
//        Node<T> temp = head;
//        Node<T> tempHead = head;
//        while(temp != null){
//
//        }
//
//
//    }

    // https://leetcode.com/problems/rotate-list/
    public void rotateList(int k){
        k = k % size();
        if(k <= 0  || head == null || head.next == null){
            return;
        }

        Node<T> secondHalf = findKthFromEnd(k +1);
        Node<T> firstHalf = secondHalf.next;

        secondHalf.next = null;

        Node<T> temp = firstHalf;
        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = head;
        head = firstHalf;
    }


    //https://leetcode.com/problems/remove-duplicates-from-sorted-list/

    public void removeDuplicates(){
        if(head == null || head.next == null){
            return;
        }

        Node<T> temp = head;

        while(temp.next != null){

            while(temp.next != null && temp.next.data == temp.data){
                temp.next = temp.next.next;
            }

            temp = temp.next;
            if(temp == null){
                break;
            }
        }
    }

//    //https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
//    public void removeDuplicatesTwo(){
//        if(head == null | head.next == null){
//            return;
//        }
//        boolean isDuplicate = false;
//        Node<T> temp = head;
//        while(temp.next != null && temp.next.data == temp.data){
//            temp = temp.next;
//            isDuplicate = true;
//        }
//
//        if( isDuplicate == true && temp.next == null){
//            head = null;
//            return;
//        }
//        if( isDuplicate == true){
//            head = temp.next;
//        }
//
//        temp = head;
//        isDuplicate = false;
//
//        while(temp.next != null){
//            if(temp.data == temp.next.data){
//                temp.next = temp.next.next;
//                isDuplicate = true;
//            }else{
//                isDuplicate = false;
//                temp = temp.next;
//            }
//        }
//
//
//    }




}

