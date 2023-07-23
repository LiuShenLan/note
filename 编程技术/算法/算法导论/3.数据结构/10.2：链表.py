"""
链表
"""
class NODE():
    def __init__(self,data,pre_index=None,next_index=None):
        self.data=data
        self.pre=pre_index
        self.next=next_index

class LINKED_LIST():
    def __init__(self):
        self.nil=NODE(None)
        self.nil.next_index=self.nil
        self.nil.pre_index=self.nil
    def insert(self,x):
        if not isinstance(x,NODE):
            x=NODE(x)
        x.next_index=self.nil.next_index
        x.pre_index=self.nil
        self.nil.next_index.pre_index=x
        self.nil.next_index=x
    def append(self,x):
        if not isinstance(x,NODE):
            x=NODE(x)
        x.next_index=self.nil
        x.pre_index=self.nil.pre_index
        self.nil.pre_index.next_index=x
        self.nil.pre_index=x
    def delete(self, x):
        x.pre_index.next_index=x.next_index
        x.next_index.pre_index=x.pre_index
    def search(self,k):
        x=self.nil.next_index
        while x!=self.nil and x.data!=k:
            x=x.next_index
        return x
    def show(self):
        x=self.nil.next_index
        while x!=self.nil:
            print(x.data,end=" ")
            x=x.next_index
        print("")

    

if __name__ == '__main__':
    print("链表,输入依次为1,2,3,4")
    temp=LINKED_LIST()
    temp.append(2)
    temp.append(3)
    temp.append(4)
    temp.insert(1)
    temp.show()
    temp.delete(temp.search(2))
    print("删除2: ",end='')
    temp.show()
    
