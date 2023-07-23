// 动态连通性

public class UnionFind {
    private int[] id;   // 父链接数组(由触点索引)
    private int[] sz;   // (由触点索引的)哥哥根节点所对应的分量的大小
    private int count;  // 连通分量的数量

    public UnionFind(N) {
        // 初始化分量id数组
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++)
            id[i] = i;
        sz = new int[N];
        for(int i = 0; i < N; i++)
            sz[i] = 1;
    }

    public int count() {
        return count;
    }

    public boolean conntcted(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        // 跟随链接找到根节点
        while (p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        // 将p和q归并到相同的分量中
        int i = find(p);
        int j = find(q);

        // 如果p和q已经在相同的分量之中则不需要采取任何行动
        if(i == j)
            return ;

        // 将小树的根节点连接到大树的根节点
        if(sz[i] < sz[j]) {
            id[i] = j;
            sz[j] +=sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}
