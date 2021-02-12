package edu.northeastern.ashish;

import javax.print.event.PrintEvent;

public class Main {

    public static void main(String[] args) {

        LinkList<Integer> list = getLinkList();
        list.removeDuplicates();
        list.printList();
        System.out.println("");

    }

    private static LinkList<Integer> getLinkList(){
        LinkList<Integer> list = new LinkList<>();
        list.addTail(1);
        list.addTail(1);
        list.addTail(1);
        list.addTail(1);
        return list;
    }


    private static Node<Integer> getNode1(){
        LinkList<Integer> list = new LinkList<>();
        list.addTail(3);
        list.addTail(5);
        list.addTail(7);
        list.addTail(2);
        return list.head;
    }

    private static Node<Integer> getNode2(){
        LinkList<Integer> list = new LinkList<>();
        list.addTail(4);
        list.addTail(8);
        list.addTail(7);
        return list.head;
    }

    private static void createCycle(LinkList<Integer> list){
        list.addTail(1);
        list.addTail(2);
        list.addTail(3);
        list.addTail(4);
        list.addTail(5);
        list.addTail(6);
        list.addTail(7);
        list.addTail(8);
        list.addTail(9);
        list.addTail(10);
        list.addTail(11);
        list.addTail(12);
        list.addTail(13);
        // this would be Node 5
        Node<Integer> startPoint = list.head.next.next.next.next;

        Node<Integer> tailNode = list.getTailNode();
        tailNode.next = startPoint;
    }


    public static void printNode(Node<Integer> node){
        while(node != null){
            System.out.println(node.data + "-> ");
            node = node.next;
        }
        System.out.println();

    }

    public static Node<Integer> sortedMerge(Node<Integer> node1, Node<Integer> node2){
        Node result = null;

        if(node1 == null){
            return node2;
        }

        if(node2 == null){
            return node1;
        }
        if(node1.data <= node2.data){
            result = node1;
            result.next = sortedMerge(node1.next, node2);
        }else{
            result = node2;
            result.next = sortedMerge(node1, node2.next);
        }

        return result;

    }


    public static Node<Integer> zipMerge(LinkList<Integer> list){
        Node<Integer> second = list.breakListInHalf();
        second = reverse(second);

        return  zipMerge(list.head, second, true);

    }

    private static Node<Integer> zipMerge(Node<Integer> node1, Node<Integer> node2, boolean bSwitch){
        Node<Integer> result = null;
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }

        if(bSwitch == true){
            result = node1;
            result.next = zipMerge(node1.next, node2, false);
        }else{
            result = node2;
            result.next = zipMerge(node1, node2.next, true);
        }

        return result;

    }

    public static Node<Integer> reverse(Node<Integer> node){
        if(node == null || node.next == null){
            return null;
        }
        Node<Integer> back = null;
        Node<Integer> mid = node;
        Node<Integer> front = node.next;

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

    //https://leetcode.com/problems/add-two-numbers/
    public static Node<Integer> addTwoNumbers(Node<Integer> node1, Node<Integer> node2){
        // we are assuming there is data in both the nodes
        node1 = reverse(node1);
        node2 = reverse(node2);

        int carry = 0 ;

        Node<Integer> temp1 = node1;
        Node<Integer> temp2 = node2;
        Node<Integer> result = null;

        while(temp1 != null || temp2 != null){
            int sum = carry;
            if(temp1 != null){
                sum += temp1.data;
            }
            if(temp2 != null){
                sum += temp2.data;
            }

            if(sum >= 10){
                carry = 1;
                sum = sum %10;
            }else{
                carry = 0;
            }

            if(temp1 != null){
                temp1 = temp1.next;
            }
            if(temp2 != null){
                temp2 = temp2.next;
            }

            Node<Integer> node = new Node<>(sum);
            node.next = result;
            result = node;

        }

        return result;



    }




}
