import java.util.Iterator;

// 定容栈
public class FixedCapacityStackOf<Item> implements  Iterator<Item> {
    private Item[] a;    // stack entries
    private int N;  // size

    public FixedCapacityStackOfStrings(int cap) {
        a = (Item[]) new Object[cap];
        /*
        直接创建泛型数组是不允许的：a = new Item[cap]   ×
        所以需要使用类型转换：a = (Item[]) new Object[cap]
        */
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        // 将元素压入栈顶
        if(N == a.length)
            resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() {
        // 从栈顶删除元素
        Item item = a[--N];
        a[N] = null;    // 避免对象游离
        if(N > 0 && N == a.length / 4)
            resize(a.length / 2)
        return item;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private void resize(int max) {
        // 将大小为 N <= max 的栈移动到一个新的大小为max的数组中
        Itemp[] temp = (Item[]) new Object[max];
        for(int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }

        public void remove() {
            ;
        }
    }
}
