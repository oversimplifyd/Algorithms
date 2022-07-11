/**
 * We essentially want to build a tree like data structure for keys appearing in a word. 
 * 
 * the boy is addict  
 * 
 *    t   b   i   a   
 *    h   o   s   d
 *    e   y       d
 * f              i
 * t              c
 *                t
 * 
 * Resources: 
 * https://www.geeksforgeeks.org/trie-insert-and-search/
 */

class Solution {

    public static void main(String[] args) {

        Trie newTrie = new Trie();

        String first = "first";
        newTrie.insert(first);

        String first_second = "second";
        newTrie.insert(first_second);

        String fra = "firm";
        newTrie.insert(fra);

        String farmer = "farmer";
        newTrie.insert(farmer);

        String farming = "farming";
        newTrie.insert(farming);

        System.out.println(newTrie.search("farmings"));
    }
}

class Trie {

    private TrieNode root = new TrieNode();

    public void insert(String word) {

        TrieNode root;
        int length = word.length(); 
        int level; 
        int index;

        TrieNode current = this.root;

        for (level = 0; level < length; level++) {
            index = word.charAt(level) - 'a';

            if (current.getChild(index) == null) {
                TrieNode child = new TrieNode();
                current.setChild(index, child);
            }

            current = current.getChild(index);
        }

        current.setIsLeaf(true);
    }

    public boolean search(String word) {

        if (word.length() == 0) return true; 

        TrieNode current = this.root; 
        int length = word.length();

        for (int level = 0; level < length; level++) {

            int index = word.charAt(level) - 'a';

            if (current.getChild(index) == null) return false; 

            current = current.getChild(index);
        }

        return current.isLeaf() == true;
    }
 }

 class TrieNode {

    private static int ALPHABET_SIZE = 25;
    private TrieNode[] children; 
    private boolean isLeaf = false; 

    private char value;

    public TrieNode() {
        this.children = new TrieNode[ALPHABET_SIZE];
    }

    public void setChild(int index, TrieNode node) {
        this.children[index] = node; 
    }

    public TrieNode getChild(int index) {
        return this.children[index];
    }

    public void setIsLeaf(boolean isLeaf){
        this.isLeaf = isLeaf;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }
}
