/**
 * Quicksort algorithm. O(NlogN)
 * @param a an array of Comparable items.
 */
public static <AnyType extends Comparable<? super AnyType>> void QuickSort(AnyType[] a) {
    QuickSort(a, 0, a.length - 1);
}

/**
 * Return median of left, center, and right.
 * Order these and hide the pivot.
 */
private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] a, int left, int right) {
    int center = (left + right) / 2;
    AnyType tmp;
    if (a[center].compareTo(a[left]) < 0) {
        tmp = a[left];
        a[left] = a[center];
        a[center] = tmp;
    }
    if (a[right].compareTo(a[left]) < 0) {
        tmp = a[left];
        a[left] = a[right];
        a[right] = tmp;
    }
    if (a[right].compareTo(a[center]) < 0) {
        tmp = a[center];
        a[center] = a[right];
        a[right] = tmp;
    }
    
    // Place pivot at position right - 1
    tmp = a[center];
    a[center] = a[right - 1];
    a[right - 1] = a[center];
    return a[right - 1]
}

/**
 * Internal quicksort method that makes recursive calls.
 * Uses median-of-three partitioning and a cutoff of 10.
 * @param a an array of Comparable items.
 * @param left the left-most index of the subarray.
 * @param right the right-most index of the subarray.
 */
private static <at extends Comparable<? super AnyType>> void QuickSort(AnyType[] a, int left, int right) {
    if (left + CUTOFF <= right) {
        AnyType pivot = median3(a, left, right);
        AnyType tmp;

        // Begin partitioning
        int i = left, j = right - 1;
        for (;;) {
            while (a[++i].compareTo(pivot) < 0) {}
            while (a[--j].compareTo(pivot) > 0) {}
            if (i < j) {
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            } else
                break;
        }

        // Restore pivot
        tmp = a[i];
        a[i] = a[right - 1];
        a[right - 1] = tmp;

        QuickSort(a, left, i - 1);  // Sort small elements
        QuickSort(a, i + 1, right); // Sort large elements
    } else  // Do an insertion sort on the subarray
        InsertionSort(a, left, right);
}

/**
 * Internal selection method that makes recursive calls.
 * Uses median-of-three partitioning and a cutoff fo 10.
 * Places the kth smallest item in a[k-1].
 * @param a an array of Comparable.
 * @param left the left-most index of the subarray.
 * @param right the right-most index of the subarray.
 * @param k the desired index (1 is minimum) in the entire array.
 */
private static <AnyType extends Comparable<? super AnyType>> void QuickSelect(AnyType[] a, int left, int right, int k) {
    if (left + CUTOFF <= right) {
        AnyType pivot = median3(a, left, right);
        AnyType tmp;

        // Begin partitioning
        int i = left, j = right - 1;
        for(;;) {
            while(a[++i].compareTo(pivot) < 0) {}
            while(a[--j].compareTo(pivot) > 0) {}
            if (i < j) {
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            } else
                break;
        }

        // Restore pivot
        tmp = a[i];
        a[i] = a[right - 1];
        a[right - 1] = tmp;

        if (k <= i)
            QuickSelect(a, left, i - 1, k);
        else
            QuickSelect(a, i + 1, right, k);
    } else  // Do an insertion sort on the subarray
        InsertionSort(a, left, right);
}