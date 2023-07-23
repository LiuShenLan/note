"""
线段树
"""

class NODE():
    def __init__(self,range_l,range_r):
        self.range_l = range_l
        self.range_r = range_r
        self.val = 0
        self._left = None
        self._right = None
    @property
    def range_m(self):
        return (self.range_l+self.range_r)//2
    @property
    def left(self):
        self._left = self._left or NODE(self.range_l,self.range_m)
        return self._left
    @property
    def right(self):
        self._right = self._right or NODE(self.range_m,self.range_r)
        return self._right

class TREE():
    def __init__(self,range_l,range_r):
        self.root=NODE(range_l,range_r)

    def insert(self,node,val,range_l,range_r):
        if node.range_l>=range_r or node.range_r<=range_l:
            return
        if node.range_l==node.range_r:
            if node.val<val:
                node.val=val
        else:
            if range_r<=node.range_m:
                self.insert(node.left,val,range_l,range_r)
            elif range_l>=node.range_m:
                self.insert(node.right,val,range_l,range_r)
            else:
                self.insert(node.left,val,range_l,range_r)
                self.insert(node.right,val,range_l,range_r)
            node.val=node.left.val+node.right.val