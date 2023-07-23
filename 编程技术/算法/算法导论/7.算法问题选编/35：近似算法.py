"""
近似算法
"""

def approx_vertex_cover(g):     # 顶点覆盖近似算法(求图的顶点的一个子集,使得图中的所有边,都有至少一个端点在这个子集中)
    c=[]
    e_=g.e
    while e_!=[]:
        edge=e_[0]
        c.append(edge[0])
        c.append(edge[1])
        for i in range(len(e_)):
            if e_[i][0]==edge[0] or e_[i][0]==edge[1] or e_[i][1]==edge[0] or e_[i][1]==edge[1]:
                del(e_[i])
                i-=1
    return c
if __name__ == '__main__':
    print("近似算法")
