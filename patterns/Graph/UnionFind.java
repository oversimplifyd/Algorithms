import java.util.*;

/**
 * A disjoint set data structure is one that supports the union and find operation. 
 * 
 * Union operation merges element in a disjoint set into a single set and returns the set. 
 * Find operation traverses agiven set in search for an element. 
 * 
 * The UnionFind is useful in detecting cycles in graph. 
 * 
 * We can also further optimize the Union and Find operation. 
 * Optimize find by doing path compression, where a child element is recursively attached to its root parent element. 
 * 
 * Optimize union, Union operation can also be optimized to make search faster by reducing the possibility of a rapdily growing subtree. 
 * This is achieved by attaching an incoming root to the root of the main subtree. 
 * 
 * A disjoint set also support a MakeSet for the initial initialization fo the set ds. 
 *
 */

 class Solution {

    public static void main(String[] args) {
        
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        DisjoinSet set = new DisjoinSet();
        set.makeSet(list);

        set.union(3, 1);
        set.union(2, 3);
        set.union(4, 2);

        System.out.println(set.find(4));
    }
 }

 class DisjoinSet {

    HashMap<Integer, Integer> parent; 
    HashMap<Integer, Integer> rank; 

    public DisjoinSet() {
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();
    }

    public void makeSet(List<Integer> elements){

        for(Integer el: elements) {
            // every elements parent initially points to itself. 
            this.parent.put(el, el);
            this.rank.put(el, 0);
        }
    }

    public int find(int el) {

        // without path compression. works extremely well for single disjoint set 
        // but grows big when multiple disjoinset unions. 
        // if (this.parent.get(el) == el) {
        //     return el;
        // }

        // return this.find(this.parent.get(el));

        // with path compression, on each find, we collapse the hierarchy. 
        if (this.parent.get(el) != el) {
            parent.put(el, this.find(parent.get(el)));
        }

        return parent.get(el);
    }

    public void union(int el1, int el2) {
        
        int x = this.find(el1);
        int y = this.find(el2);

        if (x == y) {
            return;
        }

        // Union Rank optimization
        // Always attach the smaller depth to the root of the deeper tree. 

        if (rank.get(x) > rank.get(y)) {
            this.parent.put(y, x);
        } else if (rank.get(x) < rank.get(y)) {
            this.parent.put(x, y);
        } else {
            this.parent.put(x, y);
            this.rank.put(y, this.rank.get(y) + 1);
        }
    }
 }
