package edu.northeastern.ashish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
//        System.out.println(fibTabular(54));
        //[−2, 1, −3, 4, −1, 2, 1, −5, 4],
//        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println(kadaneNaive(arr));

//        int[] arr = {50, 3, 10, 7, 40, 80};
//        System.out.println(longestIncreasingSubSequence(arr));

//        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
//        System.out.println(minJumps(arr));

        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
        System.out.println(longestBittonic(arr));
     }

    static int fibRecursive(int n){
        if(n < 0 ){
            return  Integer.MIN_VALUE;
        }
        if( n <= 1 ){
            return  n;
        }

        return  fibRecursive(n-1 ) + fibRecursive( n-2 );
    }

    static int fibMemoization( int  n ){
        if(n < 0 ){
            return  Integer.MIN_VALUE;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        return fibMemoization(n, map);

    }
    static int fibMemoization(int n , HashMap<Integer, Integer> map){
        if( !map.containsKey(n)){
            if(n <=1){
                return n;
            }
            int val = fibMemoization(n-1, map) + fibMemoization(n-2, map);
            map.put(n, val);
        }
        return map.get(n);
    }

    static int fibTabular(int n){
        if(n < 0 ){
            return  Integer.MIN_VALUE;
        }
        if( n <= 1 ){
           return n;
        }
        int[] table = new int[n+1];
        table[0] = 0;
        table[1] = 1;

        for(int i = 2; i < table.length; i ++){
            table[i] = table[i-1] + table[i-2];
        }
        return table[n];
    }


    static int kadane(int[] arr){
        int max_sum = 0;
        int max_local = 0;
        int max_start = 0;
        int max_end = 0;

        for(int i = 0 ; i < arr.length; i ++){
            max_local += arr[i];

            if(max_local < 0){
                max_local = 0;
                max_start = i + 1;
            }

            if(max_sum < max_local){
                max_sum = max_local;
                max_end = i;
            }
        }
        System.out.println("Start = " + max_start + " End = " + max_end);

        return max_sum;
    }

    static int kadaneNaive(int[] arr){

        int max_sum = 0 ;
        for(int i = 0 ; i < arr.length; i ++){
            for(int j = i ; j < arr.length; j ++){
                int local_sum  = getSum(arr, i, j);

                if(max_sum < local_sum){
                    max_sum = local_sum;
                }
            }
        }

        return  max_sum;

    }

    static int getSum(int[] arr , int start, int end){
        int sum = 0;
        for(int i = start; i < end; i ++ ){
            sum += arr[i];
        }
        return sum;
    }

    static int longestIncreasingSubSequence(int[] arr){
        int[] lis = new int[arr.length];
        int [] result =  IntStream.range(0, arr.length).toArray();

        for(int i = 0 ; i < arr.length; i ++){
            lis[i] = 1;
        }

        int max = 1;
        int max_index = 0;

        for(int i = 1; i < arr.length; i ++){

            for(int j = 0 ; j < i; j ++){
                if( arr[i] > arr[j]){// there is an increasing subsequence starting with j ending at i
                    if( lis[j] + 1 > lis[i] ){
                        lis[i] = lis[j] +1;
                        result[i] = j;
                    }
                    if(lis[i] > max){
                        max = lis[i];
                        max_index = i;
                    }
                }
            }
        }

        // Print out the values
        Stack<Integer> stack = new Stack<>();
        int count = max;
        int curr_index = max_index;
        while(count != 0 ){
            stack.push(arr[curr_index]);
            curr_index = result[curr_index];
            count --;
        }

        while(stack.size() != 0){
            System.out.printf(stack.pop() + " -> ");
        }
        System.out.println();

        return  max ;

    }

    static int minJumps(int[] arr){
        int[] jumps = new int[arr.length];
        int[] results = new int[arr.length];

        jumps[0] = 0;
        for(int i = 1; i < jumps.length; i ++){
            jumps[i] = Integer.MAX_VALUE;
        }

        for(int i = 1; i < arr.length; i ++){
            for(int j = 0 ; j < i; j ++){

                if( arr[j] + j >= i){ // if we can make the jump

                    if(jumps[j] + 1 < jumps[i]){ // if we jump from j -> i is this a smaller jump
                        jumps[i] = jumps[j] + 1;
                        results[i] = j;
                    }
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        int count = jumps[arr.length -1];
        int index = results[arr.length -1];
        stack.push(arr[arr.length -1]);
        while(count > 0){
            stack.push(arr[index]);
            index = results[index];
            count--;
        }
        while(stack.size() != 0){
            System.out.printf(stack.pop() + " -> ");
        }
        System.out.println();

        return  jumps[arr.length -1];

    }

    static int[] longestIncreasingSubsequenceArray(int[] arr){
        int[] lis = new int[ arr.length];
        for(int i = 0 ; i < arr.length; i ++){
            lis[i] = 1;
        }
        // Initialize longer increasing sub sequence
        for(int i = 1; i < arr.length; i ++){
            for(int j = 0 ; j < i; j ++){
                if( arr[i] > arr[j]){// there is an increasing subsequence starting with j ending at i
                    if( lis[j] + 1 > lis[i] ){
                        lis[i] = lis[j] +1;
                    }
                }
            }
        }

        return  lis;
    }

    static void reverseArray(int[] arr){
        int start = 0 ;
        int end = arr.length -1 ;

        while (start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start ++;
            end --;
        }
        System.out.println();
    }


    static int longestBittonic(int[] arr){
        int[] lis = longestIncreasingSubsequenceArray(arr);
        System.out.println(Arrays.toString(lis));
        reverseArray(arr);
        int[] lds = longestIncreasingSubsequenceArray(arr);
        reverseArray(lds);
        System.out.println(Arrays.toString(lds));


        int max = 0;
        for(int i = 0 ; i < arr.length; i ++){
            if( max < lis[i] + lds[i] -1 ){
                max = lis[i] + lds[i] -1;
            }
        }
        return max;
    }




}
