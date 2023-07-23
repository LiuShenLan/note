import math
import itertools
import collections

from collections import Counter
from collections import defaultdict

class Solution:
    def abc(self,nums):
        nums.sort()
        n = len(nums)
        res = 0
        for i in range(n-2):
            if nums[i] == 0:
                continue
            k = i+2
            for j in range(i+1,n-1):
                if nums[j] == 0:
                    continue
                while k < n and nums[i] + nums[j] > nums[k]:
                    k+=1
                res += k - j + 1
        return res

a=Solution()
print(a.abc([2,2,3,4]))
