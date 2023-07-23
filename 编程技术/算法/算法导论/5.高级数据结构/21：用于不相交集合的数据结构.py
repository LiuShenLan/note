"""
用于不相交集合的数据结构
"""
# 不相交集合的数据结构维护了一个集合,其元素为一个个不相交的集合
# 用一个代表来标识每个集合,代表为集合的某个成员,若两次查询动态集合的代表没有修改动态集合,那么这两次查询应该得到相同的答案

#　make_set(x):创建一个新的集合,代表(唯一元素)为x
# union(x,y):将动态结合x,y合并成一个新的动态集合
# find_set(x):返回指针,指针指向包含x的(唯一)集合的代表

# 链表表示
class LIST_NODE():
    def __init__(self,value=None,next_node=None,set_=None):
        self.value=value    # 关键字
        self.next=next_node # 下一对象
        self.set=set_  # 指向集合Si
class LIST_NODE_SET():
    def __init__(self,head=None,tail=None):
        self.head=head  # 指向第一个对象
        self.tail=tail  # 指向最后的对象
def list_make_set(x):
    L=LIST_NODE_SET()
    x=LIST_NODE(value=x,next_node=None,set_=L)
    L.head=x
    L.tail=x
    return L
def list_find_set(node):    # 返回节点所在集合的代表(Si.head)
    return node.set.head.value
def list_union(node_x,node_y):
    L1=node_x.set
    L2=node_y.set
    L1.tail.next=L2.head
    z=L2.head
    while z.next!=None:
        z.set=L1
        z=z.next
    L1.tail=L2.tail
    return L1

# 不相交集合森林
class TREE_NODE():
    def __init__(self,value=None,rank=None,p=None):
        self.value=value
        self.rank=rank  # 节点的秩,表示从该节点的高度的上界(从该节点到后代叶节点的最长简单路径上边的数目的上界)
        self.p=p
def forest_make_set(x):
    x=TREE_NODE(value=x,rank=0)
    x.p=x
def forest_find_set(x):
    if x.p!=x:
        x.p=forest_find_set(x.p)    # 使x.p直接指向根节点
    return x.p  # 路径压缩
def forest_link(x,y):
    if x.rank>y.rank:   # 按秩合并
        y.p=x
    else:
        x.p=y
        if x.rank==y.rank:
            y.rank+=1
def forest_union(x,y):
    forest_link(forest_find_set(x),forest_find_set(y))

if __name__ == '__main__':
    print("用于不相交集合的数据结构")