"""
多线程算法
"""
import threading
import time


class my_thread(threading.Thread):
    def __init__(self,thread_id,name,delay):
        threading.Thread.__init__(self)
        self.thread_in=thread_id
        self.name=name
        self.delay=delay
    def run(self):  # 线程的活动方式
        print("开始线程"+self.name)
        threadLock.acquire()    # 获取锁，用于线程同步
        print_time(self.name,self.delay,5)
        print("结束线程"+self.name)
        threadLock.release()    # 释放锁，开启下一个线程

def print_time(thread_name,delay,count):
    while count:
        time.sleep(delay)
        print("{}: {}".format(thread_name,time.ctime(time.time())))
        count-=1

if __name__ == '__main__':
    print("多线程算法")
    threadLock=threading.Lock()
    threads=[]
    # 创建新线程
    thread1=my_thread(1,"Thread-1",1)
    thread2=my_thread(2,"Thread-2",2)
    # 开启线程
    thread1.start()
    thread2.start()
    # 添加线程到线程列表
    threads.append(thread1)
    threads.append(thread2)
    # 等待所有线程完成
    for t in threads:
        t.join()
    print("退出主线程")
    