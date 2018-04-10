package com.nanShao;

/* ListSorts.java */

import list.*;

public class ListSorts {

    private final static int SORTSIZE = 10;

    /**
     *  makeQueueOfQueues() makes a queue of queues, each containing one item
     *  of q.  Upon completion of this method, q is empty.
     *  @param q is a LinkedQueue of objects.
     *  @return a LinkedQueue containing LinkedQueue objects, each of which
     *    contains one object from q.
     **/
    public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
        // Replace the following line with your solution.
        if (q.isEmpty())
            return null;
        LinkedQueue res = new LinkedQueue();
        while(!q.isEmpty()) {
            try{
                LinkedQueue temp = new LinkedQueue();
                temp.enqueue(q.dequeue());
                res.enqueue(temp);
            }catch (QueueEmptyException makeQueueOfQueues){
                makeQueueOfQueues.printStackTrace();
            }
        }
        return res;
    }

    /**
     *  mergeSortedQueues() merges two sorted queues into a third.  On completion
     *  of this method, q1 and q2 are empty, and their items have been merged
     *  into the returned queue.
     *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest
     *    to largest.
     *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest
     *    to largest.
     *  @return a LinkedQueue containing all the Comparable objects from q1
     *   and q2 (and nothing else), sorted from smallest to largest.
     **/
    public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
        // Replace the following line with your solution.
        LinkedQueue res = new LinkedQueue();

        Comparable h1, h2;
        while(!q1.isEmpty() && !q2.isEmpty()){
            try{

                h1 = (Comparable) (q1.front());
                h2 = (Comparable) (q2.front());
                if(h1.compareTo(h2) <= 0){
                    res.enqueue(q1.dequeue());
                }else{
                    res.enqueue(q2.dequeue());
                }
            }catch (QueueEmptyException mergeSortedQueues){
                mergeSortedQueues.printStackTrace();
            }
        }
        if(q1.isEmpty()){
            res.append(q2);
        }else {
            res.append(q1);
        }
        return res;
    }

    /**
     *  mergeSort() sorts q from smallest to largest using mergesort.
     *  @param q is a LinkedQueue of Comparable objects.
     **/
    public static void mergeSort(LinkedQueue q) {
        // Your solution here.
        LinkedQueue temp = makeQueueOfQueues(q);
        try{
            while(temp.size()>1){
                    LinkedQueue t1 = (LinkedQueue)temp.dequeue();
                    LinkedQueue t2 = (LinkedQueue)temp.dequeue();
                    temp.enqueue(mergeSortedQueues(t1, t2));
            }
            q.append((LinkedQueue) temp.dequeue());
        }catch (QueueEmptyException mergeSort){
            mergeSort.printStackTrace();
        }
    }

    /**
     *  partition() partitions qIn using the pivot item.  On completion of
     *  this method, qIn is empty, and its items have been moved to qSmall,
     *  qEquals, and qLarge, according to their relationship to the pivot.
     *  @param qIn is a LinkedQueue of Comparable objects.
     *  @param pivot is a Comparable item used for partitioning.
     *  @param qSmall is a LinkedQueue, in which all items less than pivot
     *    will be enqueued.
     *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
     *    will be enqueued.
     *  @param qLarge is a LinkedQueue, in which all items greater than pivot
     *    will be enqueued.
     **/
    public static void partition(LinkedQueue qIn, Comparable pivot, LinkedQueue qSmall, LinkedQueue qEquals, LinkedQueue qLarge) {
        // Your solution here.
        int n = qIn.size();
        while(n>0){
            n--;
            try{
                if(pivot.compareTo(qIn.front())<0){
                    qLarge.enqueue(qIn.dequeue());
                }else if(pivot.compareTo(qIn.front())==0){
                    qEquals.enqueue(qIn.dequeue());
                }else {
                    qSmall.enqueue(qIn.dequeue());
                }
            }catch (QueueEmptyException partition){
                partition.printStackTrace();
            }

        }
    }


    /**
     *  quickSort() sorts q from smallest to largest using quicksort.
     *  @param q is a LinkedQueue of Comparable objects.
     **/
    public static void quickSort(LinkedQueue q) {
        // Your solution here.
        if(q.size()>1){
            int pivot = (int) (Math.random()*q.size()) + 1;
            LinkedQueue qSmall = new LinkedQueue();
            LinkedQueue qEqual = new LinkedQueue();
            LinkedQueue qLarge = new LinkedQueue();
            partition(q, (Comparable)(q.nth(pivot)), qSmall, qEqual, qLarge);
            quickSort(qSmall);
            quickSort(qLarge);
            q.append(qSmall);
            q.append(qEqual);
            q.append(qLarge);
        }
    }

    /**
     *  makeRandom() builds a LinkedQueue of the indicated size containing
     *  Integer items.  The items are randomly chosen between 0 and size - 1.
     *  @param size is the size of the resulting LinkedQueue.
     **/
    public static LinkedQueue makeRandom(int size) {
        LinkedQueue q = new LinkedQueue();
        for (int i = 0; i < size; i++) {
            q.enqueue(new Integer((int) (size * Math.random())));
        }
        return q;
    }

    /**
     *  main() performs some tests on mergesort and quicksort.  Feel free to add
     *  more tests of your own to make sure your algorithms works on boundary
     *  cases.  Your test code will not be graded.
     **/
    public static void main(String [] args) {

        LinkedQueue q = makeRandom(SORTSIZE);
        System.out.println(q.toString());
        mergeSort(q);
        System.out.println(q.toString());

        q = makeRandom(10);
        System.out.println(q.toString());
        quickSort(q);
        System.out.println(q.toString());

    // Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    //
    }

}