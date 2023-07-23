"""
二叉搜索树
"""
# 二叉搜索树:左子数<=父节点<=右子树

class NODE():
    def __init__(self,key,left=None,right=None,p=None):
        self.key=key
        self.left=left
        self.right=right
        self.p=p

class TREE():
    # 左子节点<=父节点<=右子节点
    def __init__(self):
        self.root=None

    def insert(self,z):
        if not isinstance(z,NODE):
            z=NODE(z)

        y=None
        x=self.root
        while x!=None:
            y=x
            if z.key<x.key:
                x=x.left
            else:
                x=x.right
        z.p=y
        if y==None:
            self.root=z
        elif z.key<y.key:
            y.left=z
        else:
            y.right=z
    def insert_recursive(self,z):   # 递归版本
        if not isinstance(z,NODE):
            z=NODE(z)

        if self.root==None:
            self.root=z
        else:
            self._insert_recursive(None,self.root,z)
    def _insert_recursive(self,parents,child,z):
        if child==None:
            z.p=parents
            if z.key<parents.key:
                parents.left=z
            else:
                parents.right=z
        elif z.key<child.key:
            self._insert_recursive(child,child.left,z)
        else:
            self._insert_recursive(child,child.right,z)

    def delete(self, z):
        if z.left==None:
            self.transplant(z,z.right)
        elif z.right==None:
            self.transplant(z,z.left)
        else:
            y=self.minimum(z.right)
            if y.p!=z:
                self.transplant(y,y.right)  # 后继没有左子节点
                y.right=z.right
                y.right.p=y
            self.transplant(z,y)
            y.left=z.left
            y.left.p=y
    def transplant(self, before,after):     # 用树after替换树before
        if before.p==None:
            self.root=after
        elif before==before.p.left:
            before.p.left=after
        else:
            before.p.right=after
        if after!=None:
            after.p=before.p

    def search(self,root,num):
        if root==None or num==root.key:
            return root
        if num<root.key:
            return self.search(root.left,num)
        else:
            return self.search(root.right,num)
    def search_loop(self,root,num):
        while root!=None and num!=root.key:
            if num<root.key:
                root=root.left
            else:
                root=root.right
        return root

    def minimum(self,x):
        while x.left!=None:
            x=x.left
        return x
    def maximum(self,x):
        while x.right!=None:
            x=x.right
        return x

    def successor(self,x):  # 输出>=x的最小值
        if x.right!=None:
            return self.minimum(x.right)
        else:
            y=x.p
            # 搜索x的 有左子节点的 最底层父节点.即为x的后继
            while y!=None and x==y.right:
                x=y
                y=y.p
            return y
    def predecessor(self,x):  # 输出<=x的最大值
        if x.left!=None:
            return self.maximum(x.left)
        else:
            y=x.p
            # 搜索x的 有右子节点的 最底层父节点.即为x的前驱
            while y!=None and x==y.left:
                x=y
                y=y.p
            return y

    def inorder_tree_walk(self,x):  # 中序遍历,按照顺序输出树的值
        if x!=None:
            self.inorder_tree_walk(x.left)
            print(x.key,end=' ')
            self.inorder_tree_walk(x.right)
    def inorder_tree_walk_loop(self):   # 中序遍历(循环版)
        s=[]
        current=self.root
        finish_flag=False
        while not finish_flag:
            if current!=None:
                s.append(current)
                current=current.left
            else:
                if len(s)!=0:
                    current=s.pop()
                    print(current.key,end=' ')
                    current=current.right
                else:
                    finish_flag=True

"""
LeetCode

class Node():
    def __init__(self,val):
        self.val=val
        self.l=None
        self.r=None
class Tree():
    def __init__(self):
        self.root=None

    def search(self,root,val):
        if not root or root.val==val:
            return root
        return self.search(root.l,val) if val<root.val else self.search(root.r,val)

    def insert(self,root,val):
        if not root:
            return Node(val)
        if root.val<val:
            root.r=self.insert(self.root.r,val)
        elif root.val==val:
            return root
        else:
            root.l=self.insert(root.l,val)
        return root

    def successor(self,root):
        root=root.r
        while root.l:
            root=root.l
        return root.val

    def predecessor(self,root):
        root=root.l
        while root.r:
            root=root.r
        return root.val

    def delete(self,root,val):
        if not root:
            return None

        if val>root.val:
            root.r=self.delete(root.r,val)
        elif val<root.val:
            root.l=self.delete(root.l,val)
        else:
            if not root.l and not root.r:
                root=None
            elif root.r:
                root.val=self.successor(root)
                root.r=self.delete(root.r,root.val)
            else:
                root.val=self.predecessor(root)
                root.l=self.delete(root.l,root.val)
        return root
"""

if __name__ == '__main__':
    print("二叉搜索树")
    tree=TREE()
    tree.insert(5)
    tree.insert_recursive(1)
    tree.insert(3)
    tree.insert_recursive(9)
    tree.insert(0)
    tree.insert_recursive(4)
    tree.insert(7)
    tree.insert_recursive(6)
    tree.insert(2)
    tree.insert_recursive(8)
    print(tree.inorder_tree_walk(tree.root))
    tree.delete(tree.search(tree.root,5))
    print(tree.inorder_tree_walk_loop())
    print(tree.successor(tree.search(tree.root,3)).key)
    print(tree.presecessor(tree.search_loop(tree.root,3)).key)
