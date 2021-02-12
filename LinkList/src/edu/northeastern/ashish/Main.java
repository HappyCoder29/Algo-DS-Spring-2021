package edu.northeastern.ashish;

import javax.print.event.PrintEvent;

public class Main {

    public static void main(String[] args) {

        LinkList<Integer> list = getLinkList();



        System.out.println(convertBinaryToInteger(list.head));

    }

    private static LinkList<Integer> getLinkList(){
        LinkList<Integer> list = new LinkList<>();
        list.addTail(1);
        list.addTail(0);
        list.addTail(1);
        list.addTail(1);

        return list;
    }


    public static NodeRand<Integer> getRandomList(){
        NodeRand<Integer> one = new NodeRand<>(1);
        NodeRand<Integer> two = new NodeRand<>(2);
        NodeRand<Integer> three = new NodeRand<>(3);
        NodeRand<Integer> four = new NodeRand<>(4);

        one.next = two;
        two.next = three;
        three.next = four;

        one.rand = three;
        two.rand = two;
        three.rand = four;
        four.rand = two;

        return one;

    }


    public static NodeRand<Integer> createCopyOfRandomList(NodeRand<Integer> orig){
        NodeRand<Integer> tempOrig = orig;
        // Create Copy inside the Original One
        while(tempOrig != null){
            NodeRand<Integer> add = new NodeRand<>(tempOrig.data);
            add.next = tempOrig.next;
            tempOrig.next = add;
            tempOrig = tempOrig.next.next;
        }
        tempOrig = orig;
        NodeRand<Integer> tempDuplicate = orig.next;

        // we figure out what is the random pointer
        while(tempOrig != null){
            tempDuplicate.rand = tempOrig.rand.next;
            tempOrig = tempOrig.next.next;
            if(tempOrig != null){
                tempDuplicate = tempOrig.next;
            }
        }

        NodeRand<Integer> duplicate = orig.next;
        tempOrig = orig;
        tempDuplicate = duplicate;

        // this should break out the orig and duplicate
        while(tempOrig != null && tempOrig.next != null){
            tempOrig.next = tempOrig.next.next;
            if(tempOrig.next != null){
                tempDuplicate.next = tempDuplicate.next.next;
            }
            tempOrig = tempOrig.next;
            tempDuplicate = tempDuplicate.next;
        }

        return  duplicate;

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

    // https://leetcode.com/problems/partition-list/

    public static Node<Integer> partitionAroundX(Node<Integer> node, Integer x){
        if(node == null || node.next == null){
            return node;
        }

        Node<Integer> smaller = null;
        Node<Integer> larger = null;

        Node<Integer> temp = node;

        while(temp != null){
            if(temp.data < x){
                Node<Integer> add = temp;
                temp = temp.next;
                add.next = smaller;
                smaller = add;
            }else{
                Node<Integer> add = temp;
                temp = temp.next;
                add.next = larger;
                larger = add;
            }
        }

        smaller = reverse(smaller);
        larger = reverse(larger);

        node = smaller;
        while(smaller.next != null){
            smaller = smaller.next;
        }
        smaller.next = larger;

        return node;



    }

    public static Node<Integer> breakListInHalf(Node<Integer> node){
        if(node == null || node.next == null){
            return node;
        }

        Node front = node;
        Node back = node;

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


    public static Node<Integer> sortList(Node<Integer> node){


        if (node == null || node.next == null)
            return node;
        Node mid = breakListInHalf(node);
        Node left = sortList(node);
        Node right = sortList(mid);
        return  sortedMerge(left , right);

    }


    public static int convertBinaryToInteger(Node<Integer> node){
        if(node == null )
            return 0;
        node = reverse(node);

        Node<Integer> temp = node;
        int sum = 0;
        int current = 0;
        while(temp != null){
            sum += temp.data * Math.pow(2, current);
            current ++;
            temp = temp.next;
        }
        return  sum;
    }

    public static int size(Node<Integer> node){
        int size = 0;
        Node<Integer>  temp = node;
        while(temp != null){
            temp = temp.next;
            size++;
        }
        return size;
    }

    //https://leetcode.com/problems/intersection-of-two-linked-lists/
    public static Node<Integer> getIntersection(Node<Integer> node1, Node<Integer> node2){
        if(node1 == null || node2 == null){
            return null;
        }

        int size1 = size(node1);
        int size2 = size(node2);

        Node<Integer> temp1 = node1;
        Node<Integer> temp2 = node2;

        if(size1 > size2 ){
            for(int i = 0 ; i < size1-size2; i ++){
                temp1 = temp1.next;
            }
        }else{
            for(int i = 0 ; i < size2-size1; i ++){
                temp2 = temp2.next;
            }
        }

        while(temp1 != null){
            if(temp1 == temp2){
                return temp1;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return null;
    }




}
