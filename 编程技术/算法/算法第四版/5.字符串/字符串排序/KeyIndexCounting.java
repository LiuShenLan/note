public class KeyIndexCounting {
    // 键索引计数法
    // 对可以通过键将元素分为多组的数组进行排序,即键相同的元素在排序后会被聚集到一起,但相对顺序没有变化
    public KeyIndexCounting(String[] a, int R) {
        // 输入数组a中的每个元素保存了一个名字和一个键,代码a[i].key()会返回指定元素的键
        // 输入中的R为字符串所含字符所属的字母表中字母的数目
        int N = a.length;

        String[] aux = new String[N];   // 辅助数组
        int[] count = new int[R+1];

        // 计算出现频率
        // 计算每个键出现的频率,注意count[0]的值总是0
        for(int i = 0; i < N; i++)
            count[a[i].key() + 1]++;
        // 将频率转换为索引
        // 计算每个键在排序结果中的起始索引位置
        for(int r = 0; r < R; r++)
            count[r+1] += count[r];
        // 将元素分类
        // 将所有元素移动到辅助数组aux中以进行排序,每个元素在aux中[]中的位置是由它的键对应的count[]值决定
        // 在移动之后将count[]中对应元素的值加1,以保证count[]为相同键的下一个元素在aux[]的索引位置
        for(int i = 0; i < N; i++)
            aux[count[a[i].key()]++] = a[i];
        // 回写
        // 将排序的结果复制会原数组中
        for(int i = 0; i < N; i++)
            a[i] = aux[i];
    }
}