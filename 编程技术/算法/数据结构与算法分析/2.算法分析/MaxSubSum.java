//最大连续子序列和(若全为负数则输出0)

public class MaxSubSum {
    public static void main(String[] args) {
        int[] a = {-2, 11, -4, 13, -5, -2};
        System.out.println(maxSumRec(a, 0, a.length - 1));
        System.out.println(maxSum(a));
    }

    // 线性时间算法
    private static int maxSum(int[] a) {
        int maxSum = 0, thisSum = 0;

        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];
            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                thisSum = 0;
        }

        return maxSum;
    }

    // 分治算法
    private static int maxSumRec(int[] a, int l, int r) {
        if (l == r) {
            if (a[l] >= 0)
                return a[l];
            else
                return 0;
        }

        int m = (l + r) >> 1;
        int maxLeftSum = maxSumRec(a, l, m);
        int maxRightSum = maxSumRec(a, m+1, r);

        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for (int i = m; i >= l; i--) {
            leftBorderSum += a[i];
            if (leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }

        int maxRightBorderSum = 0, rightBorderSum = 0;
        for (int i = m + 1; i <= r; i++) {
            rightBorderSum += a[i];
            if (rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
        }

        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    // 返回a, b, c中的较大值
    private static int max3(int a, int b, int c) {
        if(a >= b && a >= c)
            return a;
        else if (b >= a && b >= c)
            return b;
        else
            return c;
    }
}