/**
Design a class that simulates a Stack data structure, implementing the following two operations:

push(int num): Pushes the number ‘num’ on the stack.
pop(): Returns the most frequent number in the stack. If there is a tie, return the number which was pushed later.
 */
import java.util.*;

class Entry {
  public int freq; 
  public int order; 
  public int num; 

  public Entry(int num, int freq, int order) {
    this.freq = freq;
    this.order = order; 
    this.num = num; 
  }
}

class EntryComparator implements Comparator<Entry> {
  
  public int compare(Entry e1, Entry e2) {
    if (e1.num == e2.num) {
      return e2.order - e1.order; 
    }
    return e2.freq - e1.freq; 
  }
}

class FrequencyStack {

  private Map<Integer, Integer> freqMap = new HashMap<>();
  private PriorityQueue<Entry> maxHeapFreq = new PriorityQueue<>(new EntryComparator());

  int initialOrder = 0; 

  public void push(int num) {
    freqMap.put(num, freqMap.getOrDefault(num , 0) + 1); 
    maxHeapFreq.offer(new Entry(num, freqMap.get(num), initialOrder++));
  }

  public int pop() {
    Entry currentEntry = maxHeapFreq.poll();

    if (freqMap.get(currentEntry.num) > 1) {
      freqMap.put(currentEntry.num, freqMap.get(currentEntry.num) - 1);
    } else {
      freqMap.remove(currentEntry.num);
    }

    return currentEntry.num;
  }

  public static void main(String[] args) {
    FrequencyStack frequencyStack = new FrequencyStack();
    frequencyStack.push(1);
    frequencyStack.push(2);
    frequencyStack.push(3);
    frequencyStack.push(2);
    frequencyStack.push(1);
    frequencyStack.push(2);
    frequencyStack.push(5);
    System.out.println(frequencyStack.pop());
    System.out.println(frequencyStack.pop());
    System.out.println(frequencyStack.pop());
  }
}
