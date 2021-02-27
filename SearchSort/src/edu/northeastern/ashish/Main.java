package edu.northeastern.ashish;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

//        int[] arr = {2, 4, 2, 1, 11, 15};
//        System.out.println(sumOfTwoEqualToRest(arr) );

        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8};
        int[] merged = mergedSortedArraysRecursive(arr1, arr2);
        printArr(merged);

       // twoSumOn(arr, 10);

//        int[] arr = {-1,-1,0,0,3,4,4,4,5,5,5,5,5,5,6,8,9,9,9,11,11};
//        System.out.println(findFloor(arr, 2));


//        int[] arr = {6,5,3,1,8,7,2,4};
//        int[] arr1 = {5,1,3,2,2,2,1,2,7,7,7,9,11,1,3,4,4,4,2,6};
//        int[] arr2 = {1,2,0,0,2,2,1,1,0,0,1,2,0,1};
//        int[] arr3 = {1,5,5,3,2,6,5,5,0,5};
//        dutchFlag(arr, 4);
//        System.out.println(majorityElement(arr3));

       // countSort(arr1, 12);
       // Arrays.sort(arr);
       // bubbleSort(arr);

       // selectionSort(arr);

       //  mergeSort(arr);
        //quickSort(arr);

       // findNthLargest(arr, 5);
       // printArr(arr);

    }

    private static void printArr(int[] arr){
        if(arr == null){
            return;
        }

        for (int i : arr) {
            System.out.printf(i + ", ");
        }
        System.out.println();
    }

    private static void printArrayList(ArrayList<Integer> list){
        for ( Integer i : list ) {
            System.out.printf(String.valueOf(i));
        }
        System.out.println();

    }

    private static  void swap(int[] arr, int i , int j){
        if( arr == null || arr.length < 2 || i < 0 || j < 0 || i >= arr.length || j >= arr.length){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // O(n^2)  space O(1)
    public static void bubbleSort(int[] arr){
        for(int i = 0 ; i < arr.length ; i ++){
            for(int j = 0 ; j < arr.length-i-1; j ++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    // O(n^2) space O(1)
    public static void selectionSort(int[] arr){
        for(int i = 0 ; i < arr.length ; i ++){
            int minIndex = i;
            for(int j = i +1; j < arr.length ; j ++){
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            if(minIndex != i ){
                swap(arr, minIndex, i);
            }
        }
    }


    public static void mergeSort(int[] arr){
        mergeSort(arr, 0, arr.length -1);
    }

    // Total complexity is O( nLog(n) )
    private static void mergeSort(int[] arr, int low, int high){

        if(low >= high){
            return;
        }

        int mid = (low + high)/2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid +1, high);
        merge(arr, low, high);
    }

    // Complexity of this code // Space O(n)
    private static void merge(int[] arr, int low, int high){

        int mid = (low + high)/2;
        int[] temp = new int[high-low +1];
        int i = low;
        int j = mid+1;
        int index = 0 ;

        //O(n/2)
        while(i <= mid && j <= high){
            if(arr[i] < arr[j]){
                temp[index++] = arr[i++];
            }else{
                temp[index++] = arr[j++];
            }
        }

        // O(n/2)
        // copy the remaining
        while( i <= mid){
            temp[index++] = arr[i++];
        }
        while( j <= high){
            temp[index++] = arr[j++];
        }

        // At this point temp has the sorted array
        // copy temp into original array
        // O(n)
        i = low;
        for(int k = 0 ; k < temp.length; k ++ ){
            arr[i] = temp[k];
            i ++;
        }
    }

    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length -1);
    }

    private static void quickSort(int[] arr, int low, int high){

        if(low < high){
            int pos = partition(arr, low, high);
            quickSort(arr, low, pos -1);
            quickSort(arr, pos + 1, high);
        }
    }

    // O(n)
    private static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int wall = low -1;

        for(int i = low; i < high; i ++){
            if(arr[i] < pivot){
                wall ++;
                swap(arr, i, wall);
            }
        }
        // We will put the pivot value at th wall position
        wall ++;
        swap(arr, high, wall);
        return wall;
    }

    public static void findNthLargest(int[] arr, int n){
        if( arr == null || arr.length == 0  || n < 0 || n >= arr.length){
            return;
        }
        findNthLargest(arr, n, 0, arr.length -1);
    }
    private static void findNthLargest(int[] arr, int n, int low, int high){

        if(low < high){
            int pos = partition(arr, low, high);
            if(pos == arr.length -n){
                System.out.println(arr[pos]);
                return;
            }
            findNthLargest(arr, n,  low, pos -1);
            findNthLargest(arr, n, pos + 1, high);
        }
    }


    public static void countSort(int[] arr, int RANGE){

        int[] count = new int[RANGE];

        // Increment values in count array for that pat index
        for(int i = 0 ; i < arr.length ; i ++){
            count[arr[i]] ++;
        }

        int index = 0 ;

        // Overwrite on the original array number of times
        // an element occurred.
        for(int i = 0; i < RANGE; i ++){
            while(count[i] > 0){
                arr[index] = i;
                index++;
                count[i]--;
            }
        }
    }

    // O(N)
    public static void dutchFlag(int[] arr, int pivot){
        int low = 0;
        int mid = 0;
        int high = arr.length -1;

        while(mid <= high){

            if(arr[mid] < pivot){
                swap(arr, mid ++, low ++);
            }
            else if(arr[mid] == pivot){
                mid ++;
            }else{
                swap(arr, mid, high--);
            }
        }
    }

    // O(2N) = O(n)
    public static  int majorityElement(int[] arr){

        int candidate = findCandidate(arr);

        int count = 0;
        for(int i = 0; i < arr.length; i ++){
            if(arr[i] == candidate){
                count ++;
            }
        }
        if(count > arr.length/2){
            return candidate;
        }

        return Integer.MIN_VALUE;
    }

    private static int findCandidate(int[] arr){
        int majorityCandidate = arr[0];
        int count = 1;

        for(int i = 1; i < arr.length; i ++){
            if(arr[i] == majorityCandidate){
                count++;
            }else{
                count--;
                if(count == 0){
                    count = 1;
                    majorityCandidate = arr[i];
                }
            }
        }
        return majorityCandidate;
    }

    // Complexity = nLog(n)
    private static void sortArrayWaveForm(int[] arr){
        if(arr == null ||  arr.length <=1){
            return;
        }

        Arrays.sort(arr);

        for(int i = 0 ; i < arr.length-1 ; i = i +2 ){
            swap(arr, i, i +1);
        }
        printArr(arr);
    }

    // Complexity O(n)
    private static void sortArrayWaveFormOn(int[] arr){
        for(int i = 0 ; i < arr.length ; i = i + 2) {

            if( i > 0  && arr[i-1] > arr[i]){
                swap(arr, i-1, i);
            }

            if(i < arr.length -1 && arr[i] < arr[i +1]){
                swap(arr, i, i+1);
            }
        }
        printArr(arr);
    }

    // Complexity O( log(n) )
    private static boolean binSearch(int[] arr, int x){
        if(arr == null){
            return false;
        }
        int low = 0;
        int high = arr.length -1;

        while(low <= high){
            int mid = (low + high)/2;
            if(arr[mid] > x){
                high = mid -1;
            }else if (arr[mid] < x){
                low = mid +1;
            }else{
                return true;
            }
        }
        return false;
    }

    public static boolean binSearchRecursive(int[] arr, int x){
        if(arr == null){
            return false;
        }
        return binSearchRecursive(arr, x, 0, arr.length -1);
    }

    public static boolean binSearchRecursive(int[] arr, int x, int low, int high){
        if(low > high){
            return false;
        }
        int mid = (low + high)/2;

        if(arr[mid] < x){
            return binSearchRecursive(arr,x, mid +1, high);
        }else if (arr[mid] > x){
            return binSearchRecursive(arr,x, low, mid -1);
        }
        return true;
    }

    public static int getNumberOfOccurances(int[] arr, int x){
        return getNumberOfOccurances(arr, x, 0, arr.length -1);
    }
    private static int getNumberOfOccurances(int[] arr, int x, int low, int high){
        if(low > high){
            return 0;
        }

        if(arr[high] < x){
            return 0;
        }

        if(arr[low] > x){
            return 0;
        }
        if(arr[low] == x && arr[high] == x){
            return high - low + 1;
        }

        int mid = (low + high)/2;

        if(arr[mid] < x){
           return getNumberOfOccurances(arr, x, mid +1, high);
        }else if (arr[mid] > x){
            return getNumberOfOccurances(arr, x, low, mid -1);
        }else{
            return 1 + getNumberOfOccurances(arr, x, low, mid -1) +
                    getNumberOfOccurances(arr, x, mid +1, high);
        }
    }



    public static int getFirstIndex(int[] arr, int x){
        return getFirstIndex(arr, x, 0, arr.length -1);
    }

    private static int getFirstIndex(int[] arr, int x, int low, int high){
        if(low > high || arr[low] > x || arr[high] < x){
            return -1;
        }
        if(arr[low] == x){
            return low;
        }
        int mid = (low + high)/2;
        if(arr[mid] < x){
            return getFirstIndex(arr, x, mid +1, high);
        }else if(arr[mid] > x){
            return getFirstIndex(arr, x, low, mid -1);
        }else {
            return getFirstIndex(arr,x, low, mid);
        }

    }

    public static int getLastIndex(int[] arr, int x){
        return getLastIndex(arr, x, 0, arr.length -1);
    }

    private static int getLastIndex(int[] arr, int x, int low, int high){
        if(low > high || arr[low] > x || arr[high] < x){
            return -1;
        }
        if(arr[high] == x){
            return high;
        }
        int mid = (low + high)/2;
        if(arr[mid] < x){
            return getLastIndex(arr, x, mid +1, high);
        }else if(arr[mid] > x){
            return getLastIndex(arr, x, low, mid -1);
        }else {
            return getLastIndex(arr,x, mid, high-1);
        }

    }

    public static int findCeiling(int[] arr, int x){
        return findCeiling(arr, x, 0, arr.length -1);
    }

    private static int findCeiling(int[] arr, int x, int low, int high){
        if(arr[low] > x){
            return arr[low];
        }
        if(arr[high] < x){
            return Integer.MAX_VALUE;
        }

        int mid = (low + high)/2;
        if(arr[mid] == x){
            return x;
        }else if(arr[mid] < x){
            return findCeiling(arr, x, mid +1, high);
        }else{
            return findCeiling(arr, x, low, mid);
        }
    }

    public static int findFloor(int[] arr, int x){
        return  findFloor(arr, x, 0, arr.length -1);
    }
    private static int findFloor(int[] arr, int x, int low, int high){
        if(arr[high] < x){
            return arr[high];
        }
        if(arr[low] > x ){
            return Integer.MIN_VALUE;
        }

        int mid = (low + high)/2;

        if(arr[mid] == x){
            return arr[mid];
        }else if(arr[mid] < x){
            return findFloor(arr, x, mid, high);
        }else{
            return findFloor(arr, x,low, mid -1 );
        }
    }

    // Complexity nLog(n)
    public static boolean twoSum(int[] arr, int x){
        if(arr == null || arr.length < 2){
            return false;
        }

        Arrays.sort(arr);
        int low = 0 ;
        int high = arr.length -1;

        while(low < high){
            int sum = arr[low] + arr[high];
            if( sum == x){
                return true;
            }else if(sum > x) {
                high --;
            }else{
                low ++;
            }
        }
        return false;

    }

    // Complexity O(n)
    public static void twoSumOn(int[] arr, int x){
        if(arr == null || arr.length < 2){
            return;
        }
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for(int i = 0 ; i < arr.length; i ++){
            if ( map.containsKey( x- arr[i]) ){
                printArrayList(map.get(x- arr[i]));
                System.out.print(i + ", " );
                return;
            }

            ArrayList<Integer> list;
            if(map.containsKey(arr[i])){
                list = map.get(arr[i]);
            }else{
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(arr[i], list);

        }
        return;
    }

    public static boolean sumOfTwoEqualToRest(int[] arr){
        if(arr == null || arr.length <=2 ){
            return false;
        }

        int totalSum = 0;
        for (Integer i : arr) {
            totalSum += i;
        }

        Arrays.sort(arr);
        int low = 0 ;
        int high = arr.length -1;

        while(low < high){
            int sum = arr[low] + arr[high];
            if(sum * 2 == totalSum){
                return true;
            }
            else if( sum * 2  < totalSum){
                low ++;
            }else {
                high --;
            }
        }
        return false;
    }

    static int[] mergeSortedArrays(int[] arr1, int[] arr2){
        int[] merged = new int[arr1.length + arr2.length];

        int ptr1 = 0;
        int ptr2 = 0;
        int ptr3 = 0;

        while(ptr1< arr1.length && ptr2 < arr2.length){
            if(arr1[ptr1] < arr2[ptr2]){
                merged[ptr3++] = arr1[ptr1++];
            }else {
                merged[ptr3++] = arr2[ptr2++];
            }
        }

        while(ptr1< arr1.length){
            merged[ptr3++] = arr1[ptr1++];
        }

        while(ptr2< arr2.length){
            merged[ptr3++] = arr2[ptr2++];
        }
        return merged;
    }


    static int[] mergedSortedArraysRecursive(int[] arr1, int[] arr2){
        int[] merged = new int[arr1.length + arr2.length];
        int ptr1 = 0;
        int ptr2 = 0;
        int ptr3 = 0;
        mergedSortedArraysRecursive(merged, arr1, arr2, ptr1, ptr2, ptr3);
        return  merged;
    }

    static void mergedSortedArraysRecursive(int[] merged, int[] arr1, int[] arr2, int ptr1, int ptr2, int ptr3){
       if(ptr1 == arr1.length && ptr2 == arr2.length){
           return;
       }
       if(ptr1< arr1.length && ptr2 < arr2.length){
           if(arr1[ptr1] < arr2[ptr2]){
               merged[ptr3++] = arr1[ptr1++];
           }else {
               merged[ptr3++] = arr2[ptr2++];
           }
       }else if (ptr1< arr1.length){
           merged[ptr3++] = arr1[ptr1++];
       }else{
           merged[ptr3++] = arr2[ptr2++];
       }

        mergedSortedArraysRecursive(merged, arr1, arr2, ptr1, ptr2, ptr3);

    }








}
