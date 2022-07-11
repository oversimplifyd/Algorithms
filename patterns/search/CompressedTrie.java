/**
 * Compressed Trie is basically a Trie with an additional rule:
 * 
 * This is also known as Radix Tree or Patricia (Practical Algorithm to Retrieve Information Coded in Alphanumeric) Tree
 * 
 * Resources: 
 * https://www.geeksforgeeks.org/compressed-tries/
 * 
 *   the
 *   the | hourse | them | take | house | themes 
 * 
 *   label -> t h e 
 *   word -> t h e m e
 *    
 *   t h e | t h e m | horse | take 
 *   t h e m 
 *                    
 *   root          [        t ]             [            the ]
 *     t           [   n m    ]             [    n  m        ]
 *     m           [  e       ]             [    e           ]
 * 
 *                 [        h ]             [    horse       ] 
 */

class Solution {

    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("this");
        trie.insert("there");
        trie.insert("that");
        
        trie.printInfo();

        System.out.println(trie.search("there"));
    }
}

 class Trie {
    
    private TrieNode root = new TrieNode(false);
    char[] nodeLiteral = new char[]{'a', 'b', 
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public Trie() {}

    public void insert(String word) {

        TrieNode current = this.root;
        int i = 0; 

        while (i < word.length() 
        && current.getEdgeLabel(word.charAt(i) - 'a') != null) {

            int index = word.charAt(i) - 'a'; 
            int j = 0; 
            StringBuilder currentLabel = current.getEdgeLabel(index);

            while (i < word.length() 
            && j < currentLabel.length() 
            && word.charAt(i) == currentLabel.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == currentLabel.length()) {
                // currently stored label COULD be a subset of incoming word
                current = current.getChild(index);
            } else {
                if (i == word.length()) {
                    // word is a subset of label 
                    // h o r s e
                    // h o r s e s
                    TrieNode node = new TrieNode(true);

                    TrieNode existingChild = current.getChild(index);

                    int remLabelIndex = currentLabel.charAt(j) - 'a';
                    StringBuilder remLabel = this.strCopy(currentLabel, j);

                    currentLabel.setLength(j);

                    node.setChild(remLabelIndex, existingChild);
                    node.setEdgeLabel(remLabelIndex, remLabel);

                    current.setChild(index, node);
                } else {
                    // they branched off at somepoint 
                    // h o r s e 
                    // h o n e
                    TrieNode node = new TrieNode(false);

                    TrieNode temp = current.getChild(index);
                    
                    int remLabelIndex = currentLabel.charAt(j) - 'a';
                    StringBuilder remLabel = this.strCopy(currentLabel, j);

                    currentLabel.setLength(j);

                    int remWordIndex = word.charAt(i) - 'a';
                    StringBuilder remWord = this.strCopy(word, i);

                    TrieNode existing = current.getChild(index);

                    current.setChild(index, node);

                    node.setChild(remWordIndex, new TrieNode(true));
                    node.setChild(remLabelIndex, temp);

                    node.setEdgeLabel(remLabelIndex, remLabel);
                    node.setEdgeLabel(remWordIndex, remWord);
                }

                return;
            }
        }

        if (i < word.length()) {
            TrieNode node = new TrieNode(true);

            int currentIndex = word.charAt(i) - 'a';
            StringBuilder label = this.strCopy(word, i);

            current.setChild(currentIndex, node);
            current.setEdgeLabel(currentIndex, label);
        } else {
            current.setEnd(true);
        }
    }

    public boolean search(String word) {

        TrieNode current = this.root; 
        int i = 0; 

        while (i < word.length() 
        && current.getChild(word.charAt(i) - 'a') != null) {
            int index = word.charAt(i) - 'a';
            StringBuilder currentLabel = current.getEdgeLabel(index);

            int j = 0;

            while (i < word.length() 
            && j < currentLabel.length()) {

                if (word.charAt(i) != currentLabel.charAt(j)) {
					return false;
                }

                j++;
                i++;
            }

            if (i <= word.length() 
            && j == currentLabel.length()) {
                current = current.getChild(index);
             } else {
                return false;
            }
        }

        return i == word.length() && current.isEnd();
    }

    public StringBuilder strCopy(CharSequence word, int index) {

        int wordLength = word.length() ;

        StringBuilder builder = new StringBuilder(wordLength - index);

        while (index < wordLength) {
            builder.append(word.charAt(index++));
        }

        return builder;
    }

    public void printInfo() {
        this.print(this.root);
    }

    private void print(TrieNode root) {
        
        for (int i = 0; i < this.nodeLiteral.length; i++) {

            if (root.getChild(i) != null) {
                System.out.println(this.nodeLiteral[i]);

                if (root.getEdgeLabel(i) != null) {
                    System.out.println(root.getEdgeLabel(i).toString());
                }
                System.out.println("___________");

                print(root.getChild(i));
            }
        }
    }
 }

 class TrieNode {
    
    private static int ALPHABE_SIZE = 26; 
    private TrieNode[] children; 
    private StringBuilder[] edgeLabel; 

    private boolean isEnd;

    public TrieNode(boolean isEnd) {
        this.isEnd = isEnd;
        this.children = new TrieNode[ALPHABE_SIZE];
        this.edgeLabel = new StringBuilder[ALPHABE_SIZE];
    }

    public TrieNode[] getChildren() {
        return this.children;
    }

    public TrieNode getChild(int index) {

        this.checkIndex(index);

        return this.children[index];
    }

    public void setChild(int index, TrieNode node) {

        this.checkIndex(index);

        this.children[index] = node;
    }

    public StringBuilder getEdgeLabel(int index) {

        this.checkIndex(index);

        return this.edgeLabel[index];
    }

    public void setEdgeLabel(int index, StringBuilder edgeLabel) {

        this.checkIndex(index);

        this.edgeLabel[index] = edgeLabel;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    private void checkIndex(int index) {
        if (index > ALPHABE_SIZE || index < 0) {
            throw new IllegalArgumentException("Invalid Index.");
        }
    }
 }
