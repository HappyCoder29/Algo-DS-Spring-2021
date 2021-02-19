package edu.northeastern.ashish;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] arr = {6,5,3,1,8,7,2,4};
        int[] arr1 = {5,1,3,2,2,2,1,2,7,7,7,9,11,1,3,4,4,4,2,6};
        int[] arr2 = {1,2,0,0,2,2,1,1,0,0,1,2,0,1};
        int[] arr3 = {1,5,5,3,2,6,5,5,0,5};
        dutchFlag(arr, 4);
        System.out.println(majorityElement(arr3));

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






}
