public class BinarySearch {
    private static final int NOT_FOUND = -1;

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearch(a, 5));
    }

    private static int binarySearch(Integer[] a, Integer x) {
        int low = 0, high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) >> 1;

            if (a[mid].compareTo(x) < 0)
                low = mid + 1;
            else if (a[mid].compareTo(x) > 0)
                high = mid - 1;
            else
                return mid;
        }
        return NOT_FOUND;
    }
}