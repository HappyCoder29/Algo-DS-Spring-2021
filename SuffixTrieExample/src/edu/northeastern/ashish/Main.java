package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
        SuffixTrie trie = new SuffixTrie();
        trie.addString("abaabaa");

        System.out.println(trie.findNumOfTimesRepeated("abba"));
    }
}
