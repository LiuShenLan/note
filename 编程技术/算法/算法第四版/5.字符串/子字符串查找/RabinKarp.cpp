#include <iostream>
#include <vector>
#include <list>
#include <string>
#include <cstring>
#include <stack>
#include <cmath>
#include <algorithm>
#include <set>
#include <unordered_set>

using namespace std;

// 使用RabinKarp算法输出最长重复子字符串
// 为了防止哈希碰撞使用了两套编码
class RabinKarp1 {
    string longestDupSubstring(string s) {
        auto n = s.size();
        int pow1 = rand() % 75 + 26, pow2 = rand() % 75 + 26;   // 每个字符的权
        int mod1 = rand() % (INT_MAX - 1000000006) + 1000000006;
        int mod2 = rand() % (INT_MAX - 1000000006) + 1000000006;

        vector<int> array(n);
        for (int i = 0; i < n; ++i)
            array[i] = s[i] - 'a';

        // 二分查找长度范围
        int lengthL = 1, lengthR = n;
        int maxLength = 0, maxIndex = -1;
        while (lengthL < lengthR) {
            int lengthMid = lengthL + ((lengthR - lengthL) >> 1);
            int start = check(array, lengthMid, pow1, pow2, mod1, mod2);
            if (start != -1) {
                // 有重复子字符串，查找长度更长的子字符串
                lengthL = lengthMid + 1;
                maxLength = lengthMid;
                maxIndex = start;
            } else
                // 没有重复子字符串，查找长度更短的子字符串
                lengthR = lengthMid;
        }
        return maxIndex == -1 ? "" : s.substr(maxIndex, maxLength);
    }

    // 检查数组array中是否有长度lengthMid的重复子字符串
    static int check(const vector<int> &array, int lengthMid, int pow1, int pow2, int mod1, int mod2) {
        auto n = array.size();
        long long highPow1 = calculatePow(pow1, lengthMid, mod1);
        long long highPow2 = calculatePow(pow2, lengthMid, mod2);
        long long data1 = 0, data2 = 0;
        for (int i = 0; i < lengthMid; ++i) {
            data1 = (data1 * pow1 % mod1 + array[i]) % mod1;
            data2 = (data2 * pow2 % mod2 + array[i]) % mod2;
            if (data1 < 0)
                data1 += mod1;
            if (data2 < 0)
                data2 += mod2;
        }

        // 检测是否有相同的编码
        set<pair<long long, long long>> seen;
        seen.emplace(data1, data2);
        for (int start = 1; start <= n - lengthMid; ++start) {
            data1 = (data1 * pow1 % mod1 - array[start - 1] * highPow1 % mod1 + array[start + lengthMid - 1]) % mod1;
            data2 = (data2 * pow2 % mod2 - array[start - 1] * highPow2 % mod2 + array[start + lengthMid - 1]) % mod2;
            if (data1 < 0)
                data1 += mod1;
            if (data2 < 0)
                data2 += mod2;

            // 如果包含重复编码，则返回此时的起点start
            if (seen.count(make_pair(data1, data2)))
                return start;
            else
                seen.emplace(data1, data2);
        }
        // 没有重复子字符串
        return -1;
    }

    // 计算最高位的权重
    static long long calculatePow(int pow, int length, int mod) {
        long long res = 1;
        long long contribute = pow;
        for (; length > 0; length >>= 1) {
            if (length & 1) {
                res = res * contribute % mod;
                if (res < 0)
                    res += mod;
            }
            contribute = contribute * contribute % mod;
            if (contribute < 0)
                contribute += mod;
        }
        return res;
    }
};

// 使用RabinKarp算法输出最长重复子字符串
string longestDupSubstring(string s) {
    int n = s.size(), mod = 1e9 + 7;
    string res;
    // 二分查找长度范围
    int lengthL = 0, lengthR = n;
    while (lengthL < lengthR) {
        int lengthMid = lengthL + ((lengthR - lengthL) >> 1);

        // 检查是否存在长度为lengthMid的重复子字符串
        bool find = false;
        unordered_set<int> hash;

        int code = 0;
        for (int i = 0; i < lengthMid; ++i)
            code = (int)(((long long)code * 26 + s[i] - 'a') % mod);
        hash.insert(code);

        // 快速幂计算最高位的权
        int pow = 1, count = 26, temp = lengthMid;
        while (temp) {
            if (temp & 1)
                pow = (int)((long long) pow * count % mod);
            count = (int)((long long) count * count % mod);
            temp >>= 1;
        }

        for (int i = 1; i + lengthMid <= n; ++i) {
            code = (int)(((long long)code * 26 - (long long)(s[i - 1] - 'a') * pow % mod + mod) % mod);
            code = (int)(((long long)code + s[i + lengthMid - 1] - 'a') % mod);

            // 数组包含哈希值，检测是否发生哈希冲突
            if (hash.count(code)) {
                string sub = s.substr(i, lengthMid);
                if(strstr(s.data(), sub.data()) != (s.data() + i)) {
                    find = true;
                    res = sub;
                    break;
                }
            }
            hash.insert(code);
        }
        if (find)
            lengthL = lengthMid + 1;
        else
            lengthR = lengthMid;
    }
    return res;
}