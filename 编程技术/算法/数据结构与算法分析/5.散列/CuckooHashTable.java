import java.util.Random;

public interface HashFamily<AnyType> {
    int hash(AnyType x, int which);
    int getNumberOfFunctions();
    void generateNewFunctions();
}

public class CuckooHashTable<AnyType> {
    /**
     * Construct the hash table.
     * @param hf the hash family
     */
    public CuckooHashTable(HashFamily<? super AnyType> hf) {
        this(hf, DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table.
     * @param hf the hash family
     * @param size the approximate initial size
     */
    public CuckooHashTable(HashFamily<? super AnyType> hf, int size) {
        allocateArray(nextPrime(size));
        doClear();
        hashFunctions = hf;
        numHashFunctions = hf.getNumberOfFunctions();
    }

    public void makeEmpty() {
        doClear();
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return true if item is found.
     */
    public boolean contains(AnyType x) {
        return findPos(x) != -1;
    }

    /**
     * Compute the hash code for x using specified hash function.
     * @param x the item
     * @param which the hash function
     * @return the hash code
     */
    private int myhash(AnyType x, int which) {
        int hashVal = hashFunctions.hash(x, which);

        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;
        
        return hashVal;
    }

    /**
     * Method that searched all hash function places.
     * @param x the item to search for.
     * @return the position where the search terminates, or -1 if not found.
     */
    private int findPos(AnyType x) {
        for (int i = 0; i < numHashFunctions; i++) {
            int pos = myhash(x, i);
            if (array[pos] != null && array[pos].equals(x))
                return pos;
        }

        return -1;
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     * @return true if item was found and removed
     */
    public boolean remove(AnyType x) {
        int pos = findPos(x);

        if (pos != -1) {
            array[pos] = null;
            currentSize--;
        }

        return pos != -1;
    }

    /**
     * Insert into the hash table. If the item is already present, return false.
     * @param x the item to insert.
     */
    public insert(AnyType x) {
        if (contains(x))
            return false;
        
        if (currentSize >= array.length * MAX_LOAD)
            expand();
        
        return insertHelper1(x)
    }

    private void expand() {
        rehash((int) (array.length / MAX_LOAD));
    }

    private void rehash() {
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }

    private void rehash(int newLength) {
        AnyType[] oldArray = array;
        allocateArray(nextPrime(newLength));
        currentSize = 0;

        // Copy table over
        for (AnyType str : oldArray)
            if (str != null)
                insert(str);
    }

    private void doClear() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }

    private void allocateArray(int arraySize) {
        array = (AnyType[]) new Object[arraySize];
    }

    private static final double MAX_LOAD = 0.4;
    private static final int ALLOWED_REHASHES = 1;
    private static final int DEFAULT_TABLE_SIZE = 101;

    private final HashFamily<? super AnyType> hashFunctions;
    private final int numHashFunctions;
    private AnyType[] array;
    private int currentSize;

    private int rehashes = 0;
    private Random r = new Random();

    private boolean insertHelper1(AnyType x) {
        final int COUNT_LIMIT = 100;

        while (true) {
            int lastPos = -1;
            int pos;

            for (int count = 0; count < COUNT_LIMIT; count++) {
                for (int i = 0; i < numHashFunctions; i++) {
                    pos = myhash(x, i);

                    if (array[pos] == null) {
                        array[pos] = x;
                        currentSize++;
                        return true;
                    }
                }

                // none of the spots are available. Evict out a random one
                int i = 0;
                do {
                    pos = myhash(x, r.nextInt(numHashFunctions));
                } while (pos == lastPos && i++ < 5);

                AnyType tmp = array[lastPos = pos];
                array[pos] = x;
                x = tmp;
            }

            if (++rehashes > ALLOWED_REHASHES) {
                expand();   // Make the table bigger
                rehashes = 0;   // Reset the # of rehashes
            } else
                rehash();   // Same table size, new hash functions
        }
    }

    private boolean insertHelper2(AnyType x)
    {
        final int COUNT_LIMIT = 100;
        
        while(true) {
            for(int count = 0; count < COUNT_LIMIT; count++) {
                int pos = myhash(x, count % numHashFunctions);

                AnyType tmp = array[pos];
                array[pos] = x;

                if(tmp == null)
                    return true;
                else
                    x = tmp;
            }
        
            if(++rehashes > ALLOWED_REHASHES) {
                expand();      // Make the table bigger
                rehashes = 0;
            } else
                rehash( );
        }
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

public class StringHashFamily implements HashFamily<String> {
    private final int[] MULTIPLIERS;
    private final java.util.Random r = new java.util.Random();

    public StringHashFamily(int d) {
        MULTIPLIERS = new int[d];
        generateNewFunctions();
    }

    public int getNumberOfFunctions() {
        return MULTIPLIERS.length;
    }

    public void generateNewFunctions() {
        for (int i = 0; i < MULTIPLIERS.length; i++)
            MULTIPLIERS[i] = r.nextInt();
    }

    public int hash (String x, int which) {
        final int multiplier = MULTIPLIERS[which];
        int hashVal = 0;

        for (int i = 0; i < x.length(); i++)
            hashVal = multiplier * hashVal + x.charAt(i);
        
        return hashVal;
    }
}