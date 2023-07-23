import java.util.Arrays;

public class SuffixArray {
	// 后缀数组
	// 解决最长重复子字符串问题(最长重复子字符串在后缀数组中必定相邻)

	private Suffix[] suffixes;  // 后缀数组

	// 从给定字符串初始化后缀数组
	public SuffixArray(String text) {
		int n = text.length();
		this.suffixes = new Suffix[n];
		for(int i = 0; i < n; i++)
			suffixes[i] = new Suffix(text, i);  // 构建后缀数组
		Arrays.sort(suffixes);  // 将后缀数组排序
	}

	// 字符串后缀
	private static class Suffix implements Comparable<Suffix> {
		private final String text;  // 原始字符串
		private final int index;	// 该后缀在原始字符串中开始的索引

		private Suffix(String text, int index) {
			this.text = text;
			this.index = index;
		}

		// 后缀长度
		private int length() {
			return text.length() - index;
		}

		// 后缀中第i个元素
		private char charAt(int i) {
			return text.charAt(index + i);
		}

		// 将该后缀与后缀that相比较
		public int compareTo(Suffix that) {
			if(this == that)
				return 0;   // 优化
			int n = Math.min(this.length(), that.length());
			for(int i = 0; i < n; i++) {
				if(this.charAt(i) < that.charAt(i))
					return -1;
				if(this.charAt(i) > that.charAt(i))
					return +1;
			}
			return this.length() - that.length();   // 字符相同时,较短者排序靠前
		}

		// 该后缀的字符串表示
		public String toString() {
			return text.substring(index);
		}
	}

	//返回输入字符串的长度(与后缀数组长度相同)
	public int length() {
		return suffixes.length;
	}

	// 返回后缀数组中第i个元素在原始字符串中的开始索引
	public int index(int i) {
		return suffixes[i].index;
	}

	// 返回后缀数组中第i个元素和第i-1个元素的最长公共前缀的长度
	public int lcp(int i) {
		return lcpSuffix(suffixes[i], suffixes[i-1]);
	}

	// 返回后缀s和t的最长公共前缀长度
	private static int lcpSuffix(Suffix s, Suffix t) {
		int n = Math.min(s.length(), t.length());
		for(int i = 0; i < n; i++) {
			if(s.charAt(i) != t.charAt(i))
				return i;
		}
		return n;
	}

	// 返回后缀数组中第i个元素的字符串表示
	// select(rank(key))是后缀数组中第一个以key为前缀的后缀字符串
	public String select(int i) {
		return suffixes[i].toString();
	}

	// 返回后缀数组中小于query的后缀数量(二分查找)
	// rank(select(i)) = i (0 <= i <= n-1)
	public int rank(String query) {
		int lo = 0, hi = suffixes.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = compare(query, suffixes[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else if(cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	// 比较query与suffix
	private static int compare(String query, Suffix suffix) {
		int n = Math.min(query.length(), suffix.length());
		for(int i = 0; i < n; i++) {
			if(query.charAt(i) < suffix.charAt(i))
				return -1;
			if(query.charAt(i) > suffix.charAt(i))
				return +1;
		}
		return query.length() - suffix.length();	// 字符相同时,较短者排序靠前
	}
}
