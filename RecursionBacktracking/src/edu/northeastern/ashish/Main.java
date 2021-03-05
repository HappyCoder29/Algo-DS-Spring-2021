package edu.northeastern.ashish;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //generateBinarySequence(3);
        //generateMarySequence(3, 10);
        //combinations("ABC", 4);
       // generateAllSubsets("ABC");
      //  int[] arr = {10,10, 5, 1, 25, 20};
      //  generateAllSequenceSumEqualtoK(arr, 25);
      //  permutations("ABC");
        numOfWaysForDeadLockInUSElections();
    }

    //O(2 ^ n)
    public static void generateBinarySequence(int n){
        if(n <= 0){
            return;
        }

        int[] result = new int[n];

        int current = 0;
        generateBinarySequence(result, current);

    }

    private static void generateBinarySequence(int[] result, int current){

        // Base condition
        if( current == result.length ){
            // Print array
            System.out.println(Arrays.toString(result));
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            generateBinarySequence(result, current +1);
        }
    }

    // m = number of integers
    // n = size of the array
    public static void generateMarySequence(int n , int m){
        if(n <= 0 || m <= 0){
            return;
        }
        int[] result = new int[n];
        int current = 0;
        generateMarySequence(result, current, m);

    }

    private static void generateMarySequence(int[] result, int current , int m) {
        // Base condition
        if( current == result.length ){
            // Print array
            System.out.println(Arrays.toString(result));
            return;
        }

        for(int i = 0 ; i < m; i ++){
            result[current] = i;
            generateMarySequence(result, current+1, m);
        }
    }

    // O(m ^ n) where m = str.length() and n = size
    public static void combinations(String str, int size){
        if(size <= 0 || str.isEmpty()){
            return;
        }
        char[] result = new char[size];
        int current = 0;
        combinations(result, current, str);
    }

    private static void combinations(char[] result, int current, String str){
        // Base condition
        if(current == result.length){
            System.out.println(new String(result));
            return;
        }

        for(int i = 0 ; i < str.length(); i ++){
            result[current] = str.charAt(i);
            combinations(result, current +1, str);
        }
    }


    public static void generateAllSubsets(String str){
        if(str.isEmpty()){
            return;
        }
        int[] result = new int[str.length()];
        int current = 0;
        generateAllSubsets(result, current, str);
    }

    public static void generateAllSubsets(int[] result, int current, String str){

        if(current == result.length){
            printSubsets(result, str);
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            generateAllSubsets(result, current + 1, str);
        }
    }

    private static void printSubsets(int[] result, String str){
        char[] arr = str.toCharArray();

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i = 0 ; i < result.length; i ++){
            if(result[i] == 1){
                sb.append(arr[i] + ", ");
            }
        }
        if(sb.length() > 1){
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("}");
        System.out.println(sb.toString());

    }

    public static void generateAllSequenceSumEqualtoK(int[] arr, int k){
        if(k <= 0 || arr.length == 0){
            return;
        }

        int[] result = new int[arr.length];
        int current = 0;
        generateAllSequenceSumEqualtoK(result, current, arr, k);
    }

    private static void generateAllSequenceSumEqualtoK(int[] result, int current, int[] arr, int k){
        if(current == result.length){
            printSumEqualToK(result, arr, k);
            return;
        }

        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            generateAllSequenceSumEqualtoK(result, current +1, arr, k);
        }
    }

    private static void printSumEqualToK(int[] result, int[] arr, int k){

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int sum = 0;
        for(int i = 0 ; i < result.length; i ++){
            if(result[i] == 1){
                sum += arr[i];
                sb.append(arr[i] + ", ");
            }
        }
        if(sb.length() > 1){
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append("}");

        if(sum == k){
            System.out.println(sb.toString());
        }

    }

    public static void permutations(String str){
        if(str.isEmpty()){
            return;
        }
        char[] result = new char[str.length()];
        int current = 0;
        permutations(result, current, str);

    }
    public static void permutations(char[] result, int current, String str) {

        if(current == result.length){
            System.out.println(new String(result));
            return;
        }
        for(int i = 0 ; i < str.length(); i ++){
            if(isValidPermutation(result, current, i, str  )){
                result[current] = str.charAt(i);
                permutations(result, current + 1, str);
            }
        }
    }

    // Valid permutation is where the character we are trying to add has not occurred
    // in the result array from 0 till current
    private static boolean isValidPermutation(char[] result, int current, int num, String str){
        for(int i = 0 ; i < current; i ++){
            if(result[i] == str.charAt(num)){
                return false;
            }
        }
        return true;
    }

    public static void numOfWaysForDeadLockInUSElections(){
        int[] numOfElectorialVotes = {  4, 10, 16, 6, 20, 10, 11, 16, 15, 29, 38};

        int[] result = new int[numOfElectorialVotes.length];
        int current = 0;
        numOfWaysForDeadLockInUSElections(result, current, numOfElectorialVotes);
    }

    private static void numOfWaysForDeadLockInUSElections(int[] result, int current, int[] numOfElectorialVotes){

        if(current == result.length){
            //System.out.println(Arrays.toString(result));
            // Check if there is a deadlock if there is print it
            if(isThereADeadlock(result, numOfElectorialVotes)) {
                System.out.println("There is a deadlock");
                System.out.println(Arrays.toString(result));
            }
            return;
        }

        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            numOfWaysForDeadLockInUSElections(result, current +1, numOfElectorialVotes);
        }
    }

    private static boolean isThereADeadlock(int[] result, int[] numOfElectorialVotes){
        int sum = 182;

        for(int i = 0 ; i < result.length; i ++){
            // You have won that state
            if(result[i] == 1){
                sum += numOfElectorialVotes[i];
            }
        }
        if(sum == 269){
            return true;
        }
        return false;

    }







}
