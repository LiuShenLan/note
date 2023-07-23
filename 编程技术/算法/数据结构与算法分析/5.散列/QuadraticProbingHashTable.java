public class QuadraticProbingHashTable<AnyType> {
    /**
     * Construct the hash table.
     */
    public QuadraticProbingHashTable() {
        this.(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construct the hash table.
     * @param size the approximate initial size.
     */
    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    /**
     * Make the hash table logically empty.
     */
    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return the matching item.
     */
    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActivate(currentPos);
    }

    /**
     * Insert into the hash table. If the item is already present, do nothing.
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        // Insert x as active
        int currentPos = findPos(x);
        if (isActivate(currentPos))
            return ;
        
        array[currentPos] = new HashEntry<>(x, true);

        // Rehash
        if (currentSize > array.length / 2)
            rehash();
    }

    /**
     * Remove from the hash table.
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActivate(currentPos))
            array[currentPos].isActivate = false;
    }

    private static class HashEntry<AnyType> {
        public AnyType element;     // the element
        public boolean isActivate;  // false if marked deleted

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean i) {
            element = e;
            isActivate = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<AnyType>[] array; // The array of elements
    private int currentSize;    // The number of occupied cells

    /**
     * Internal method to allocate array.
     * @param arraySize the size of the array.
     */
    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    /**
     * Return true if currentPos exists and is active.
     * @param currentPos the result of a call to findPos.
     * @return true if currentPos is active.
     */
    private boolean isActivate(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActivate;
    }

    /**
     * Method that performs quadratic probing resolution in half-empty table/
     * @param x the item to search for.
     * @return the position where the search terminates.
     */
    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash(x);

        while (array[currentPos] != null && !array[currentPos].element.euqals(x)) {
            currentPos += offset;   // Compute ith probe
            offset += 2;    // f(i) = f(i-1) + 2i - 1
            if (currentPos >= array.length)
                currentPos -= array.length;
        }

        return currentPos;
    }

    /**
     * Rehashing for quadratic probing hash table.
     */
    private void rehash() {
        HashEntry<AnyType>[] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray(nextPrime(2 * oldArray.length));
        currentSize = 0;

        // Copy table over
        for (int i = 0; i < oldArray.length; i++)
            if (oldArray[i] != null && oldArray[i].isActivate)
                insert(oldArray[i].element)
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