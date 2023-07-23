#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <numeric>

using namespace std;

// 自己写的，可能有bug

string longestDupSubstring(string s) {
	int n = s.size(), maxLength = -1, maxIndex = -1;
	vector<int> suffix(n);
	iota(suffix.begin(), suffix.end(), 0);

	auto cmp = [&](const int a, const int b) -> bool {
		for (int i = 0; a + i < n && b + i < n; ++i)
			if (s[a+i] != s[b+i])
				return s[a+i] < s[b+i];
		return a < b;
	};
	sort(suffix.begin(), suffix.end(), cmp);

	for (int i = 0; i < n - 1; ++i) {
		int count = 0;
		while (suffix[i] + count < n && suffix[i+1] + count < n && s[suffix[i] + count] == s[suffix[i+1] + count])
			++count;
		if (count > maxLength) {
			maxLength = count;
			maxIndex = suffix[i];
		}
	}
	if (maxLength > 0)
		return s.substr(maxIndex, maxLength);
	return "";
}