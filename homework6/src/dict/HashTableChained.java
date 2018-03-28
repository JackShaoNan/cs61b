/* HashTableChained.java */

package dict;

import java.util.ArrayList;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

    /**
     *  Place any data fields here.
     **/
    private EntryChained[] hashTable;
    private int[] collision;
    private int n;
    private int N;


    /**
     *  Construct a new empty hash table intended to hold roughly sizeEstimate
     *  entries.  (The precise number of buckets is up to you, but we recommend
     *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
     **/

    public HashTableChained(int sizeEstimate) {
        // Your solution here.
        N = getPrime((int)(sizeEstimate/0.75));
        hashTable = new EntryChained[N];
        collision = new int[N];
        for(int i=0; i<N; ++i){
            collision[i] = -1;
        }
        n = 0;
    }

    //找到最近的最大的质数
    private int getPrime(int num){
        if(isPrime(num))
            return num;
        else{
            while(!isPrime(num)){
                num+=1;
            }
            return num;
        }
    }

    //判断是否是质数
    private boolean isPrime(int num){
        if(num<=1)
            return false;
        else{
            for(int i=2; i<Math.sqrt(num); ++i){
                if(num%i==0)
                    return false;
            }
            return true;
        }
    }

    /**
     *  Construct a new empty hash table with a default size.  Say, a prime in
     *  the neighborhood of 100.
     **/

    public HashTableChained() {
        // Your solution here.
        N = 97;
        hashTable = new EntryChained[97];
        collision = new int[N];
        for(int i=0; i<N; ++i){
            collision[i] = -1;
        }
        n = 0;
    }

    /**
     *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of hash table) - 1.
     *
     *  This function should have package protection (so we can test it), and
     *  should be used by insert, find, and remove.
     **/

    int compFunction(int code) {
        // Replace the following line with your solution.
        return ((3*code+7)%109345121)%N;
    }

    /**
     *  Returns the number of entries stored in the dictionary.  Entries with
     *  the same key (or even the same key and value) each still count as
     *  a separate entry.
     *  @return number of entries in the dictionary.
     **/

    public int size() {
        // Replace the following line with your solution.
        return n;
    }

    /**
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/

    public boolean isEmpty() {
        // Replace the following line with your solution.
        if(n==0)
            return true;
        else
            return false;
    }

    /**
     *  Create a new Entry object referencing the input key and associated value,
     *  and insert the entry into the dictionary.  Return a reference to the new
     *  entry.  Multiple entries with the same key (or even the same key and
     *  value) can coexist in the dictionary.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/

    public Entry insert(Object key, Object value) {
        // Replace the following line with your solution.
        EntryChained temp = new EntryChained();
        temp.key = key;
        temp.value = value;
        temp.setNext(null);
        int index = compFunction(key.hashCode());
        if(index>=0 && index<=N-1){
            if(hashTable[index] == null)
                hashTable[index] = new EntryChained();
            hashTable[index].setNext(temp);
            collision[index]++;
            n++;
            return temp;
        }else{
            return null;
        }

    }

    /**
     *  Search for an entry with the specified key.  If such an entry is found,
     *  return it; otherwise return null.  If several entries have the specified
     *  key, choose one arbitrarily and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/

    public Entry find(Object key) {
        // Replace the following line with your solution.
        int index = compFunction(key.hashCode());
        if(index<0 || index>N-1)
            return null;
        else{
            if(hashTable[index] == null)
                return null;
            else
                return hashTable[index].getNext();
        }

    }

    /**
     *  Remove an entry with the specified key.  If such an entry is found,
     *  remove it from the table and return it; otherwise return null.
     *  If several entries have the specified key, choose one arbitrarily, then
     *  remove and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */

    public Entry remove(Object key) {
        // Replace the following line with your solution.
        int index = compFunction(key.hashCode());
        if(index<0 || index>N-1)
            return null;
        else{
            if(hashTable[index] == null)
                return null;
            if(hashTable[index].getNext()!=null){
                EntryChained temp = hashTable[index].getNext();
                hashTable[index].setNext(temp.getNext());
                collision[index]--;
                n--;
                return temp;
            }else
                return null;

        }
    }

    /**
     *  Remove all entries from the dictionary.
     */
    public void makeEmpty() {
        // Your solution here.
        for(int i=0; i<N-1; ++i){
            if(hashTable[i] == null)
                continue;
            else {
                collision[i] = -1;
                hashTable[i] = null;
            }
        }
    }


    //print collision
    public void printCollision(){
        System.out.println("-1 for empty, 0 for one entry, num > 0 for collision.");
        int sum = 0;
        for(int i=0; i<N; ++i){
            System.out.println(i+"' collision number: "+ collision[i]);
            if(collision[i]!=-1)
                sum+=collision[i];
        }
        System.out.println("N is "+N);
        System.out.println("n is "+n);
        double exCollision = (double)(n -N + N*Math.pow((double)(1-1.0/N), (double)n));
        System.out.println("the expected number of collisions is "+exCollision);
        System.out.println("the real number of collisions is "+ sum);
        System.out.println("your real/expect : "+ sum/exCollision);
    }

    public double getAccuracy(){
        int sum = 0;
        for(int i=0; i<N; ++i){
            if(collision[i]!=-1)
                sum+=collision[i];
        }
        double exCollision = (double)(n -N + N*Math.pow((double)(1-1.0/N), (double)n));
        return sum/exCollision;
    }

}