/**
 * Standard heapsort. O(NlogN)
 * @param a an array of Comparable items.
 */
public static <AnyType extends Comparable<? super AnyType>> void HeapSort(AnyType[] a) {
    for (int i = a.length / 2 - 1; i >= 0; i--) // buildHeap
        percDown(a, i, a.length);
    
    for (int i = a.length - 1; i > 0; i--) {    // deleteMax
        AnyType tmp = a[0];
        a[0] = a[i];
        a[i] = tmp;

        percDown(a, 0, i);
    }
}

/**
 * Internal method for HeapSort.
 * @param i the index of an item in the heap.
 * @return the index of the left child
 */
private static int leftChild(int i) {
    return 2 * i + 1;
}

/**
 * Internal method for heapsort that is used in deleteMax and buildHeap
 * @param a an array of Comparable items.
 * @param i the position from which to percolate down.
 * @param n the logical size of the binary heap.
 */
private static <AnyType extends Comparable<? super AnyType>> void percDown(AnyType[] a, int i, int n) {
    int child;
    AnyType tmp;

    for (tmp = a[i]; leftChild[i] < n; i = child) {
        child = leftChild(i);
        if (child != n-1 && a[child].compareTo(a[child+1]) < 0)
            child++;
        if (tmp.compareTo(a[child]) < 0)
            a[i] = a[child];
        else
            break;
    }
    a[i] = tmp;
}
