"""
计算集合学
"""

class point():
    def __init__(self,x,y):
        self.x=x
        self.y=y

def segments_intersect(p1,p2,p3,p4):    # 判断线段p1p2与线段p3p4是否相交
    d1=direction(p3,p4,p1)
    d2=direction(p3,p4,p2)
    d3=direction(p1,p2,p3)
    d4=direction(p1,p2,p4)

    if d1*d2<0 and d3*d4<0:
        return True
    elif d1==0 and on_segment(p3,p4,p1):
        return True
    elif d2==0 and on_segment(p3,p4,p2):
        return True
    elif d3==0 and on_segment(p1,p2,p3):
        return True
    elif d4==0 and on_segment(p1,p2,p4):
        return True
    else:
        return False

def direction(pi,pj,pk):    # 计算线段pk-pi与pj-pi的X乘
    return (pk.x-pi.x)*(pj.y-pi.y)-(pj.x-pi.x)*(pk.y-pi.y)
def on_segment(pi,pj,pk):   # 判断点pk是否在线段pipj上
    if min(pi.x,pj.x) <= pk.x <= max(pi.x,pj.x) and min(pi.y,pj.y) <= pk.y <= max(pi.y,pj.y):
        return True
    else:
        return False

# def graham_scan(Q):
    # 选取p0为点集中y最小的点,若y值相同,则取其中x最小的
    # 将点集按照以p0为极点,对p1~pm按照极角从小到大排序
    # if m<2:
    #     return "凸包为空"
    # else:
    #     s=[]
    #     s.append(p0)
    #     s.append(p1)
    #     s.append(p2)
    #     for i in range(3,m+1):
    #         while s[-2],s[-1],pi为非向左转:
    #             del(s[-1])
    #     return s

if __name__ == '__main__':
    print("计算集合学")
    p1=point(0,0)
    p2=point(2,2)
    p3=point(1,1)
    p4=point(0,0)
    print(segments_intersect(p1,p2,p3,p4))