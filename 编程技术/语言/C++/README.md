- [C++笔记](#c笔记)
	- [1. 优化](#1-优化)
		- [1.1 std::move函数](#11-stdmove函数)
		- [1.2 优化输入输出](#12-优化输入输出)
	- [2. 字符串与数字相互转换](#2-字符串与数字相互转换)
	- [3. 查找](#3-查找)
		- [3.1 二分查找](#31-二分查找)
	- [4. 求和](#4-求和)
	- [5. vector](#5-vector)
		- [5.1 赋值](#51-赋值)
		- [5.2 最值](#52-最值)
		- [5.3 元素为pair的vector](#53-元素为pair的vector)
		- [5.4 改变大小](#54-改变大小)
		- [随机返回链表中的某个节点的值(O(1)空间复杂度)](#随机返回链表中的某个节点的值o1空间复杂度)
	- [6. 优先队列](#6-优先队列)

# C++笔记

## 1. 优化

### 1.1 std::move函数

`std::move`函数可以以非常简单的方式将左值引用转换为右值引用

C++标准库使用比如`vector::push_back`等这类函数时,会对参数的对象进行复制,连数据也会复制。这就会造成对象内存的额外创建,本来原意是想把参数push_back进去就行了,通过`std::move`，可以避免不必要的拷贝操作

`std::move`是将对象的状态或者所有权从一个对象转移到另一个对象，只是转移，没有内存的搬迁或者内存拷贝所以可以提高利用效率,改善性能

对指针类型的标准库对象并不需要这么做

### 1.2 优化输入输出

```C++
#include <iostream>
#include <string>

static int io_sync_off = []()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	return 0;
}();
```

1. `std::ios::sync_with_stdio(false);`

iostream默认是与stdio关联在一起的，以使两者同步，因此消耗了iostream不少性能。C++中的`std::cin`和`std::cout`为了兼容C，保证在代码中同时出现`std::cin`和`scanf`或`std::cout`和`printf`时输出不发生混乱，所以C++用一个流缓冲区来同步C的标准流。通过`std::ios_base::sync_with_stdio`函数设置为false后可以解除这种同步，让`std::cin`和`std::cout`不再经过缓冲区，iostream的性能就会提高了很多倍。因此，当解除同步之后，注意不要与`scanf`和`printf`混用以免出现问题

2. `std::cin.tie(nullptr);`

`std::cin`默认是与`std::cout`绑定的，所以每次操作的时候都要调用`fflush`，这样增加了IO的负担，通过`tie(nullptr)`来解除`std::cin`和`std::cout`之间的绑定，进一步加快执行效率

## 2. 字符串与数字相互转换

```C++
string s = to_string(x);	// 数字转字符串

int i = stoi(s);	// 字符串转数字
double d = stod(s);	// 字符串转数字
float f = stof(s);	// 字符串转数字
```

* strtol

```C++
long int strtol (const char* str, char** endptr, int base);
// str是要转换的字符
// enptr是指向第一个不可转换的字符位置的指针
// base是字符串所使用的进制
```

参数base范围从2至36，或0。参数base代表采用的进制方式，如base值为10则采用10进制，若base值为16则采用16进制等。当base值为0时则是采用10进制做转换，但遇到如'0x'前置字符则会使用16进制做转换、遇到'0'前置字符而不是'0x'的时候会使用8进制做转换。

一开始strtol()会扫描参数nptr字符串，跳过前面的空格字符，直到遇上数字或正负符号才开始做转换，再遇到非数字或字符串结束时('\0')结束转换，并将转换数值返回。参数endptr指向停止转换的位置，若字符串nptr的所有字符都成功转换成数字则endptr指向串结束符'\0'。判断是否转换成功，应检查**endptr是否为'\0'。

## 3. 查找

### 3.1 二分查找

```C++
// 在从小到大的排序数组中↓：

iterator lower_bound(begin,end,num);
// 从数组的begin位置到end-1位置二分查找第一个>=num的数字
// 找到返回该数字的地址，不存在则返回end
// 通过返回的地址减去起始地址begin,得到找到数字在数组中的下标

iterator upper_bound(begin,end,num);
// 从数组的begin位置到end-1位置二分查找第一个>num的数字
// 找到返回该数字的地址，不存在则返回end
// 通过返回的地址减去起始地址begin,得到找到数字在数组中的下标


// 在从大到小的排序数组中，重载lower_bound()和upper_bound()

iterator lower_bound(begin,end,num,greater<type>());
// 从数组的begin位置到end-1位置二分查找第一个小于或等于num的数字
// 找到返回该数字的地址，不存在则返回end
// 通过返回的地址减去起始地址begin,得到找到数字在数组中的下标

iterator upper_bound(begin,end,num,greater<type>());
// 从数组的begin位置到end-1位置二分查找第一个小于num的数字
// 找到返回该数字的地址，不存在则返回end
// 通过返回的地址减去起始地址begin,得到找到数字在数组中的下标
```

## 4. 求和
```C++
#include <numeric>

int sum = accumulate(vec.begin(), vec.end(), initVal);
// 累加求和，头两个参数指定要累加的元素范围，第三个形参是累加的初值

string sum = accumulate(vec.begin() , vec.end() , stringVal);
// 从stringVal开始把所有的vec中的字符串连接起来

// TODO: 自定义数据类型的处理
```

## 5. vector

### 5.1 赋值

```C++
void *memset(void *s, int v, size_t n);
// s:数组名/指针	v:要填充的值	n:要填充的字节数
// memset 函数是内存赋值函数，用来给某一块内存空间进行赋值的
// 包含在<string.h>头文件中,可以用它对一片内存空间逐字节进行初始化

iota(v.begin(), v.end(), val);
// 对迭代器范围内进行赋值，第一个赋值为val，第二个赋值为val+1，以此类推
// iota函数定义如下
template <class ForwardIterator, class T>
void iota(ForwardIterator first, ForwardIterator last, T val) {
	while (first != last) {
		*first = val;
		++first;
		++val;
	}
}
```

### 5.2 最值

```C++
vector<int> v:
int max = *max_element(v.begin(),v.end());
int min = *min_element(v.begin(),v.end());
```

### 5.3 元素为pair的vector

```C++
vector<pair<int, int>> v;
sort(v.begin(), v.end());
// 按照pair.first元素升序排序，pair.first相同则比较pair.second
```

使用由pair组成的vector排序，比使用二维数组+自定义谓词排序快(?)

### 5.4 改变大小

vector的`reserve`操作增加了vector的capacity，但不改变size：`reserve`是容器预留空间，但在空间内不真正创建元素对象，所以在没有添加新的对象之前，不能引用容器内的元素。加入新的元素时，要调用`push_back()`/`insert()`函数

vector的`resize`操作改变了vector的capacity同时也增加了它的size：`resize`是改变容器的大小，再创建对象，因此，调用这个函数之后，就可以引用容器内的对象了，因此当加入新的元素时，用`operator[]`操作符，或者用迭代器来引用元素对象。此时再调用`push_back()`函数，是加在这个新的空间后面的。但是如果resize的新的大小小于原来的大小时，resize不做任何操作，直接返回

### 随机返回链表中的某个节点的值(O(1)空间复杂度)

水塘抽样

从链表头开始遍历整个链表，对遍历到的第i个节点，计算[0,i)内的一个随机数，如果为0，则将返回值设为节点i的值，然后继续向后遍历，直到遍历完所有的节点↓：

```C++
class Solution {
public:
	ListNode *head;
	Solution(ListNode* head) : head(head){}

	int getRandom() {
		int i = 1, res = 0;
		for (ListNode *node = head; node;
			node = node->next, ++i)

			if (rand() % i == 0)
				res = node->val;
		return res;
	}
};
```

## 6. 优先队列

```C++
auto cmp =
	[&nums1, &nums2]
	(const pair<int, int> &a, const pair<int, int> &b)
	-> bool {

	return nums1[a.first] + nums2[a.second] > nums1[b.first] + nums2[b.second];
};
priority_queue<pair<int, int>,
				vector<pair<int,int>>,
				decltype(cmp)> pq(cmp);

// 小项堆，队列内元素为nums1和nums2内元素索引所构成的pair
// 按照nums1与nums2中元素之和的大小排序，堆顶为最小的元素

// 优先队列默认为大顶堆
```
