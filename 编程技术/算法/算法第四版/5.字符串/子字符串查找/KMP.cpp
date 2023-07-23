#include <string>
#include <vector>

using namespace std;

int KMP(string txt, string pattern) {
	int n = txt.size(), m = pattern.size();
	if (m == 0)
		return 0;

	vector<int> dfa(m);
	for (int i = 1, j = 0; i < m; ++i) {
		while (j > 0 && pattern[i] != pattern[j])
			j = dfa[j - 1];
		if (pattern[i] == pattern[j])
			++j;
		dfa[i] = j;
	}

	for (int i = 0, j = 0; i < n; ++i) {
		while (j > 0 && txt[i] != pattern[j])
			j = dfa[j-1];
		if (txt[i] == pattern[j])
			++j;
		if (j == m)
			return i - m + 1;
		return -1;
	}
}
