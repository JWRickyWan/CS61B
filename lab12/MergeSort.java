import edu.princeton.cs.algs4.Queue;

import java.util.Set;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        if(items==null){
            return null;
        }
        Queue<Queue<Item>> SingleItemQueues = new Queue<>();
        for(Item item:items){
            Queue<Item> singleItem = new Queue<>();
            singleItem.enqueue(item);
            SingleItemQueues.enqueue(singleItem);
        }
        return SingleItemQueues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        if(q1 ==null){
            return q2;
        }
        if(q2==null){
            return q1;
        }
        Queue<Item> merged = new Queue<>();
        int x = q1.size()+q2.size();
        for(int i =0;i<x;i++){
            merged.enqueue(getMin(q1,q2));
        }
        return merged;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if(items.size()<=1){
            return items;
        }
        if(items.size()==2){
            Queue<Queue<Item>> twoItem = makeSingleItemQueues(items);
            return mergeSortedQueues(twoItem.dequeue(),twoItem.dequeue());
        }
        Queue<Item> Half = new Queue<>();
        for(int i =0;i<items.size()/2;i++){
            Half.enqueue(items.dequeue());
        }
        return mergeSortedQueues(mergeSort(items),mergeSort(Half));
    }
    public static void main(String[] args){
        Queue<Integer> items = new Queue<>();
        items.enqueue(2);
        items.enqueue(1);
        items.enqueue(5);
        items.enqueue(0);
        items.enqueue(6);
        for(int item:items){
            System.out.print(item+" ");
        }
        System.out.println("");
        items = mergeSort(items);
        for(int item:items){
            System.out.print(item+" ");
        }
    }
}
