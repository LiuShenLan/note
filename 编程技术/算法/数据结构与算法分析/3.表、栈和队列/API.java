// java.util 各接口方法

public interface Collections<AnyType> extends Iterable<AnyType> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(AnyType x);
    boolean add(AnyType x);
    boolean remove(AnyType x);
    java.util.Iterator<AnyType> iterator();
}

public interface Iterator<AnyType> {
    // Iterator在集合结构改变之后将不再合法(除Iterator.remove())
    boolean hasNext();  // 是否存在下一项
    AnyType next();     // 给出集合中尚未见到的下一项
    void remove();      // 删除由next()最新返回的项，再次调用next()之前无法调用remove()
}

public interface List<AnyType> extends Collections<AnyType> {
    AnyType get(int idx);
    AnyType set(int idx, AnyType newVal);
    void add(int idx, AnyType x);
    void remove(int idx);

    ListIterator<AnyType> listIterator(int pos);
}

public interface ListIterator<AnyType> extends Iterator<AnyType> {
    // 从后向前遍历
    boolean hasPrevious();
    AnyType previous();

    void add(AnyType x);    // 将x以当前位置(next()和previous()返回的项之间)放入List中
    void set(AnyType x);    // 改变被迭代器看到的最后一个值
}