from java.util import *;

public class SeparateChainingHashTable<AnyType> {
    /**
     * Construct the hash table.
     */
    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table.
     * @param size approximate table size.
     */
    public SeparateChainingHashTable(int size) {
        theLists = new LinkedLIst[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++)
            theLists[i] = new LinkedList<>();
    }

    /**
     * Insert into the hash table. If the item is already present, then do nothing.
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);

            if (++currentSize > theLists.length)
                rehash();
        }
    }

    /**
     * Remove from the hash table.
     * @param x the item ro remove.
     */
    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        is(whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    /**
     * Find a item in the hash table.
     * @param x the item to search for.
     * @return true if x is not found.
     */
    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++)
            theLists[i].clear();
        currentSize = 0;
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<AnyType>[] theLists;
    private int currentSize;

    /**
     * Rehashing for separate chaining hash table.
     */
    private void rehash() {
        List<AnyType>[] oldLists = theLists;

        //Create new double-sized, empty table
        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++)
            theLists[j] = new LinkedList<>();
        
        // Copy table over
        currentSize = 0;
        for (int i = 0; i < oldLists.length; i++)
            for (AnyType item : oldLists[i])
                insert(item);
    }

    private int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if (hashVal < 0)
            hashVal += theLists.length;
        
        return hashVal;
    }

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        
        for ( ; !isPrime(n); n += 2)
            ;
        
        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n <= 3)
            return n > 1;
        if (n % 6 != 1 && n % 6 != 5) 
            return false;
        
        int sqrt = (int) Math.sqrt(n);
        for (int i = 5; i <= sqrt; i += 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
    }
}