"""
栈与队列
"""
# 栈:先入后出
#　队列:先入先出

class STACK():
    def __init__(self):
        self.stack=[]
    def is_enpty(self):
        return self.stack==[]
    def push(self,a):
        self.stack.append(a)
    def pop(self):
        if self.is_enpty():
            raise LookupError("栈下溢,underflow")
        else:
            return self.stack.pop()

class QUEUE():
    def __init__(self):
        self.queue=[]
    def is_empty(self):
        return self.queue==[]
    def enqueue(self,a):
        self.queue.append(a)
    def dequeue(self):
        if self.is_empty():
            raise LookupError("underflow")
        else:
            return self.queue.pop(0)
    

if __name__ == '__main__':
    print("栈与队列,输入依次为1,2,3,4")
    print(" 栈 :",end='')
    stack=STACK()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    while not stack.is_enpty():
        print(stack.pop(),end=' ')
    print("\n队列:",end='')
    queue=QUEUE()
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    queue.enqueue(4)
    while not queue.is_empty():
        print(queue.dequeue(),end=' ')
    print('')
