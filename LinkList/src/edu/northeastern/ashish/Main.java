package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
	// write your code here
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
}
