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

//        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
//        System.out.println(longestBittonic(arr));

        //System.out.println(longestCommonSubsequence("AGATAB", "GXTXAYB"));
        int[] arr = {2,5,6,9};

        System.out.println(getMinCoins(arr, 11));
     }


    //region Done in Class 1

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
    //endregion

    static int longestCommonSubstring(String str1, String str2){
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        int max = 0;
        int max_index = -1;

        int[][] matrix = new int[arr1.length +1][arr2.length +1];

        int row = matrix.length;
        int col = matrix[0].length;

        for(int i = 1; i < row  ; i ++ ){
            for(int j = 1; j < col; j ++){
                if(arr1[i-1] == arr2[j-1]) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;

                    if(max < matrix[i][j]){
                        max = matrix[i][j];
                        max_index = i - 1;
                    }
                }
            }
        }
        System.out.println(str1.substring(max_index - max + 1, max_index + 1));
        return max;
    }

    static int longestCommonSubsequence(String str1, String str2){
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int[][] matrix = new int[arr1.length +1][arr2.length +1];

        int row = matrix.length;
        int col = matrix[0].length;

        // We start with 1 since 0th one is for null string
        for(int i = 1; i < row  ; i ++ ){
            for(int j = 1; j < col; j ++){
                // We added an extra col and row so we are reducing by 1
                if(arr1[i-1] == arr2[j-1]){
                    matrix[i][j] = matrix[i-1][j-1] +1;
                }else{
                    matrix[i][j] = Math.max( matrix[i-1][j], matrix[i][j-1] );
                }
            }
        }
        int count = matrix[row-1][col -1];
        Stack<Character> stack = new Stack<>();

        int index1 = row -1;
        int index2 = col -1;
        while(count != 0 ){
            if( arr1[index1 -1] == arr2[index2 -1] ) {
                stack.push(arr1[index1 -1]);
                count  --;
                index1 --;
                index2 --;
            }else{
                // top one is bigger so the value came from top or both of them are same so we can go to top
                if(matrix[index1 - 1 ][index2] >= matrix[index1][index2 -1]){
                    index1 --;
                }else {
                    index2 --;
                }
            }
        }

       while(stack.size() != 0){
           System.out.print(stack.pop() + " ");
       }
        System.out.println();

        return matrix[row-1][col -1];

    }

    static int getMinCoins(int[] coins, int total){
        int[][] matrix = new int[coins.length + 1][total +1];

        int row = matrix.length;
        int col = matrix[1].length;

        for(int i = 0 ; i < row; i ++){
            for(int j = 1 ; j < col; j ++){
                matrix[i][j] = Integer.MAX_VALUE - 2;
            }
        }

        for(int i = 1 ; i < row; i ++){
            for(int j = 1 ; j < col; j ++){

                // if the coin value is bigger than the col
                // this coin cannot be used in this case we will get all the values from top
                if( coins[i-1] > j ){
                    matrix[i][j] = matrix[i-1][j];
                }
                else{

                    int test = matrix[i][j- coins[i-1]];
                    int test1 = matrix[i-1][j];
                    matrix[i][j] = Math.min(    matrix[i][j- coins[i-1]] + 1, // Same row subtract coins value
                                                matrix[i-1][j] ); // Same col top value
                    // get the min of these two and update Matrix
                }

            }
        }

//        int count = matrix[row-1][col -1];
//        Stack<Integer> stack = new Stack<>();
//        int index1 = row -1;
//        int index2 = col -1;
//        while(count != 0 ){
//            // If the top one is smaller than the one in same row then the value came from \
//            // top row
//
//            if( matrix[index1 -1 ][index2] ==  matrix[index1][index2 - coins[index1-1]]  + 1  ){
//                index1 --;
//                stack.push(coins[index1-1]);
//                count -- ;
//            }
//            else if (matrix[index1 -1 ][index2] <  matrix[index1][index2 - coins[index1-1]]  + 1) {
//                index1 --;
//            }else{
//                stack.push(coins[index2-1]);
//                index2  =  index2 - coins[index1-1];
//                count --;
//            }
//
//
//        }
//
//        while(stack.size() != 0){
//            System.out.print(stack.pop() + " ");
//        }
//        System.out.println();

        return matrix[row-1][col -1];


    }





}
