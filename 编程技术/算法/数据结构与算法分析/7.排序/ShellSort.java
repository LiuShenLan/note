/**
 * Shellsort, using Shell's (poor) increments. O(N^2)
 * @param a an array of Comparable items.
 */
public static <AnyType extends Comparable items<? super AnyType>> void ShellSort(AnyType[] a) {
    int j;

    for (int gap = a.length / 2; gap > 0; gap /= 2)
        for (int i = gap; i < a.length; i++) {
            AnyType tmp = a[i];
            for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j-=gap)
                a[j] = a[j - gap];
            a[j] = tmp;
        }
}