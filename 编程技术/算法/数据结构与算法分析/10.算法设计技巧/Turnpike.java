// 回溯算法-收费公路重建问题
boolean Turnpike(int[] x, DistSet d, int n) {
    x[1] = 0;   // 数组下标从1开始
    x[n] = d.deleteMax();
    x[n - 1] = d.deleteMax();
    if (d.contains(x[n] - x[n - 1])) {
        d.remove(x[n] - x[n - 1]);
        return place(x, d, n, 2, n - 2);
    } else
        return false;
}

/**
 * Backtracking algorithm to place the points x[left] ... x[right].
 * x[1] ... x[left - 1] and x[right + 1] ... x[n] already tentatively placed.
 * If place returns true, then x[left] ... x[right] will have values.
 */
boolean place(int[] x, DistSet d, int n, int left, int right) {
    int dmax;
    boolean found = false;

    if (d.isEmpty())
        return true;
    
    dmax = d.findMax();

    // Check if setting x[right] = dmax is feasible.
    if (d.contains(Math.abs(x[j] - dmax)) for all 1 <= j < left and right < j <= n) {
        x[right] = dmax;    // Try x[right] = dmax
        for (1 <= j < left, right < j <= n)
            d.remove(Math.abs(x[j] - dmax));
        found = place(x, d, n, left, right - 1);

        if (!found)     // Backtrack
            for(1 <= j < left, right < j <= n)      // Undo the deletion
                d.insert(Math.abs(x[j] - dmax));
    }

    // If first attempt failed, try to see if setting x[left] = x[n] - dmax is feasible.
    if (!found && d.contains(Math.abs(x[n] - dmax - x[j])) for all 1 <= j < left and right < j <= n) {
        x[left] = x[n] - dmax;  // Same logic as before
        for (1 <= j < left, right < j <= n)
            d.remove(Math.abs(x[n] - dmax - x[j]));
        found = place(x, d, n, left + 1, right);

        if (!found)     // Backtrack
            for(1 <= j < left, right < j <= n)      // Undo the deletion
                d.insert(Math.abs(x[n] - dmax - x[j]));
    }
    return found;
}