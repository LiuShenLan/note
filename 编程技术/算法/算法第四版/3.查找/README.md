### 简单的符号表实现的成本总结
|算法(数据结构)|查找(最坏情况)|插入(最坏情况)|查找(平均情况)|插入(平均情况)|是否高效地支持有序性相关的操作|关键接口|内存使用(字节)|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|顺序查找(无序链表)|N|N|N/2|N|否|equals()|48N|
|二分查找(有序数组)|lgN|N|lgN|N/2|是|compareTo()|16N|
|二叉查找树|N|N|1.39lgN|1.39lgN|是|compareTo()|64N|
|2-3树查找(红黑树)|2lgN|2lgN|1.00lgN|1.00lgN|是|compareTo()|64N|
|拉链法(链表数组)|<lgN|<lgN|n/(2M)|N/M|否|equals() hashCode()|48N+64M|
|线性探测法(并行数组)|clgN|clgN|<1.5|<2.5|否|equals() hashCode()|32N~128N|

### 符号表的各种实现的优缺点
|使用的数据结构|实现|优点|缺点
|:-:|:-:|:-:|:-:|
|链表(顺序查找)|SequentialSearchST|适用于小型问题|对于大型符号表很慢|
|有序数组(二分查找)|BinarySearchST|最优的查找效率和空间需求，能够进行有序性相关的操作|插入操作很慢|
|二叉查找树|BST|实现简单，能够进行有序性相关的操作|没有性能上界的保证，链接需要额外的空间|
|平衡二叉查找树|RedBlackBST|最优的查找和插入效率，能够进行有序性相关的操作|链接需要额外的空间|
|散列表|SeparateChainHashST LinearProbingHashST|能够快速地查找和插入常见类型的数据|需要计算每种类型的数据的散列，无法进行有序性相关的操作，链接和空节点需要额外的空间|
