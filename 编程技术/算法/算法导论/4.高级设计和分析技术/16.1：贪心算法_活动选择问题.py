"""
贪心算法
"""
# 活动选择问题

class ACTIVITY_SELECTOR():
    def __init__(self,time_start,time_finish):
        self.time_start=time_start.copy()
        self.time_finish=time_finish.copy()
    
    def recursive(self):    # 递归版本
        self.time_start_recursive=self.time_start.copy()
        self.time_finish_recursive=self.time_finish.copy()
        self.time_start_recursive.insert(0,0)
        self.time_finish_recursive.insert(0,0)
        return self._recursive(0,len(self.time_finish_recursive))
    
    def _recursive(self,begin,stop):
        m=begin+1
        while m<stop and self.time_start_recursive[m]<self.time_finish_recursive[begin]:
            m+=1
        if m<stop:
            return m-1,self._recursive(m,stop)
        else:
            return
    
    def iterate(self):      # 迭代版本
        n=len(self.time_start)
        result=[0]
        k=0
        for m in range(1,n):
            if self.time_start[m]>=self.time_finish[k]:
                result.append(m)
                k=m
        return result


if __name__ == '__main__':
    time_start=[1,3,0,5,3,5,6,8,8,2,12]
    tiem_finish=[4,5,6,7,9,9,10,11,12,14,16]
    action=ACTIVITY_SELECTOR(time_start,tiem_finish)
    print(action.recursive())
    print(action.iterate())
