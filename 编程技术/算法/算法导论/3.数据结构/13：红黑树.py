"""
红黑树
"""
# 每个节点或为红或为黑;根节点和叶节点为黑;红节点的子节点为黑
# 每个节点到其后代叶节点的简单路径上,黑节点数目相同(数目为黑高,不包括该节点)
class NODE():
    def __init__(self,key,color=None,left=None,right=None,p=None):
        self.key=key
        self.left=left
        self.right=right
        self.p=p
        self.color=color


class RB_TREE():
    # 左子节点>=父节点>=右子节点
    def __init__(self):
        self.nil=NODE(key=None,color='b')
        self.root=self.nil

    def insert(self,z):
        if not isinstance(z,NODE):
            z=NODE(z)

        y=self.nil
        x=self.root
        while x!=self.nil:
            y=x
            if z.key<x.key:
                x=x.left
            else:
                x=x.right
        z.p=y
        if y==self.nil:
            self.root=z
        elif z.key<y.key:
            y.left=z
        else:
            y.right=z
        
        z.left=self.nil
        z.right=self.nil
        z.color='r'
        self.insert_fixup(z)
    def insert_fixup(self,z):
        while z.p.color=='r':
            if z.p==z.p.p.left:
                y=z.p.p.right   # y为z的叔节点
                if y.color=='r':
                    z.p.color='b'
                    y.color='b'
                    z.p.p.color='r'
                    z=z.p.p
                elif z==z.p.right:
                    z=z.p
                    self.left_rotate(z)
                    z.p.color='b'
                    z.p.p.color='r'
                    self.right_rotate(z.p.p)
                else:
                    z.p.color='b'
                    z.p.p.color='r'
                    self.right_rotate(z.p.p)
            else:
                y=z.p.p.left   # y为z的叔节点
                if y.color=='r':
                    z.p.color='b'
                    y.color='b'
                    z.p.p.color='r'
                    z=z.p.p
                elif z==z.p.left:
                    z=z.p
                    self.right_rotate(z)
                    z.p.color='b'
                    z.p.p.color='r'
                    self.left_rotate(z.p.p)
                else:
                    z.p.color='b'
                    z.p.p.color='r'
                    self.left_rotate(z.p.p)
        self.root.color='b'
    
    def delete(self,z):
        y=z
        y_old_color=y.color
        if z.left==self.nil:
            x=z.right
            self.transplant(z,z.right)
        elif z.right==self.nil:
            z=z.left
            self.transplant(z,z.left)
        else:
            y=self.minimum(z.right)
            y_old_color=y.color
            x=y.right
            if y.p==z:
                x.p=y
            else:
                self.transplant(y,y.right)
                y.right=z.right
                y.right.p=y

            self.transplant(z,y)
            y.left=z.left
            y.left.p=y
            y.color=z.color
        if y_old_color=='b':
            self.delete_fixup(x)
    def delete_fixup(self,x):
        while x!=self.root and x.color=='b':
            if x==x.p.left:
                w=x.p.right     # x的兄弟节点
                if w.color=='r':
                    w.color='b'
                    x.p.color='r'
                    self.left_rotate(x.p)
                    w=x.p.right
                if w.left.color=='b' and w.right.color=='b':    # 左黑右黑
                    w.color='r'
                    x=x.p
                elif w.right.color=='b':    # 左红右黑
                    w.left.color='b'
                    w.color='r'
                    self.right_rotate(w)
                    w=x.p.right

                    w.color=x.p.color
                    x.p.color='b'
                    w.right.color='b'
                    self.left_rotate(x.p)
                    x=self.root
                else:   # 左黑右红
                    w.color=x.p.color
                    x.p.color='b'
                    w.right.color='b'
                    self.left_rotate(x.p)
                    x=self.root
            else:
                w=x.p.left     # x的兄弟节点
                if w.color=='r':
                    w.color='b'
                    x.p.color='r'
                    self.right_rotate(x.p)
                    w=x.p.left
                if w.right.color=='b' and w.left.color=='b':    # 左黑右黑
                    w.color='r'
                    x=x.p
                elif w.left.color=='b':    # 左红右黑
                    w.right.color='b'
                    w.color='r'
                    self.left_rotate(w)
                    w=x.p.left

                    w.color=x.p.color
                    x.p.color='b'
                    w.left.color='b'
                    self.right_rotate(x.p)
                    x=self.root
                else:   # 左黑右红
                    w.color=x.p.color
                    x.p.color='b'
                    w.left.color='b'
                    self.right_rotate(x.p)
                    x=self.root
        x.color='b'
    def transplant(self,before,after):  # 用树after替换树before,只修改与父节点关系
        if before.p==self.nil:
            self.root=after
        elif before==before.p.left:
            before.p.left=after
        else:
            before.p.right=after
        after.p=before.p

    def left_rotate(self,x):
        if x.right==self.nil:
            raise KeyError("input({}).right==tree.nil".format(x.key))

        y=x.right

        x.right=y.left
        if y.left!=self.nil:
            y.left.p=x
        
        y.p=x.p
        if x.p==self.nil:
            self.root=y
        elif x==x.p.left:
            x.p.left=y
        else:
            x.p.right=y
        
        y.left=x
        x.p=y
    def right_rotate(self,x):
        if x.left==self.nil:
            raise KeyError("input({}).left==tree.nil".format(x.key))

        y=x.left

        x.left=y.right
        if y.right!=self.nil:
            y.right.p=x
        
        y.p=x.p
        if x.p==self.nil:
            self.root=y
        elif x==x.p.right:
            x.p.right=y
        else:
            x.p.left=y
        
        y.right=x
        x.p=y

    def minimum(self,x):
        while x.left!=None:
            x=x.left
        return x
    def maximum(self,x):
        while x.right!=None:
            x=x.right
        return x

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


    def inorder_tree_walk(self,x):  # 中序遍历,按照顺序输出树的值
        if x!=None:
            self.inorder_tree_walk(x.left)
            if x.key!=None:
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



if __name__ == '__main__':
    print("红黑树")
    tree=RB_TREE()
    tree.insert(41)
    tree.insert(38)
    tree.insert(31)
    tree.insert(12)
    tree.insert(19)
    tree.insert(8)

    print("输入为:41,38,31,12,19,8\n排序后:",end='')
    tree.inorder_tree_walk(tree.root)

    
    i=8
    print("\n删除{:2d}:".format(i),end='')
    tree.delete(tree.search(tree.root,i))
    tree.inorder_tree_walk(tree.root)
    i=12
    print("\n删除{:2d}:".format(i),end='')
    tree.delete(tree.search(tree.root,i))
    tree.inorder_tree_walk(tree.root)
    i=19
    print("\n删除{:2d}:".format(i),end='')
    tree.delete(tree.search(tree.root,i))
    tree.inorder_tree_walk(tree.root)
    i=31
    print("\n删除{:2d}:".format(i),end='')
    tree.delete(tree.search(tree.root,i))
    tree.inorder_tree_walk(tree.root)
    i=38
    print("\n删除{:2d}:".format(i),end='')
    tree.delete(tree.search(tree.root,i))
    tree.inorder_tree_walk(tree.root)
    i=41
    print("\n删除{:2d}:".format(i),end='')
    tree.delete(tree.search(tree.root,i))
    tree.inorder_tree_walk(tree.root)
