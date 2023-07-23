public class RedBlackBST<Key extends Comparable<Key>, Value> {
    /* 红黑树的定义
    一: 与2-3查找树一一对应(将红链接相连的节点合并,得到的就是一颗2-3树)
    红链接将两个2-节点连接起来构成一个3-节点
    黑链接则是2-3树中的普通链接

    二: 与一等价的定义
    红链接均为左链接
    没有任何一个节点同时和两条红链接相连
    该树是完美黑色平衡的,即任意黑色空链接到根节点的路径上的黑链接数量相同

    三: 指向该节点的链接(该节点与父节点之间的链接)的颜色记为节点的颜色,约定空链接为黑色
    红节点均为黑节点的左子节点
    红色节点的兄弟节点或父节点或子节点不能为红节点
    任意空节点到根节点的路径上的黑节点数量相同
    */

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;    // 键
        private Value val;  // 相关联的值
        private Node left, right;   // 左右子树
        private boolean color;  // 由其父节点指向它的链接的颜色
        private int size;   // 这棵子树中的节点总数

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    private boolean isRed(Node x) {
        if(x == null)
            return false;
        return x.color == RED;
    }

    public void put(Key key, Value val) {
        // 查找key,找到则更新其值,否则为它新建一个节点
        if(val == null) {
            delete(key);
            return ;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if(h == null)   // 标准的插入操作,和父节点用红链接相连
            return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if(cmp < 0)
            h.left = put(h.left, key, val);
        else if(cmp > 0)
            h.right = put(h.right, key, val);
        else
            h.val = val;

        if(isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if(isRed(h.left)  &&  isRed(h.left.left))
            h = rotateRight(h);
        if(isRed(h.left)  &&  isRed(h.right))
            flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    // 删除操作需要从3-节点或4-节点删除,因此从根节点向下查找时保证查找经过的路径上所有节点均是3-节点或4-节点
    public void deleteMin() {
        if(isEmpty())
            throw new NoSuchElementException("BST underflow");

        if(!isRed(root.left) && !isRed(root.right)) // 根节点是2-节点并且它的两个子节点都是2-节点
            root.color = RED;   // 将根节点变为4-节点

        root = deleteMin(root);

        if(!isEmpty())
            root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if(h.left == null)
            return null;

        if(!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMax() {
        if(isEmpty())
            throw new NoSuchElementException("BST underflow");

        if(!isRed(root.left) && !isRed(root.right))  // 根节点是2-节点并且它的两个子节点都是2-节点
            root.color = RED;   // 将根节点变为4-节点

        root = deleteMax(root);

        if(!isEmpty())
            root.color = BLACK;
    }

    private Node deleteMax(Node h) {
        if(isRed(h.left))
            h = rotateRight(h);

        if(h.right == null)
            return null;

        if(!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    public void delete(Key key) {
        if(!contains(key))
            return ;

        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);

        if(!isEmpty())
            root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if(key.compareTo(h.key) < 0) {
            if(!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }else {
            if(isRed(h.left))
                h = rotateRight(h);
            if(key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if(!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if(key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }else
                h.right = delete(h.right, key);
        }
        return balance(h);
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if(isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        flipColors(h);
        if(isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if(isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if(isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right))
            flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    // 树是否为2-3查找树
    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if(x == null)
            return true;
        if(isRed(x.right))
            return false;
        if(x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }

    private boolean isBalanced() {
        int black = 0;     // 根节点到最小键的路径上的黑色链接的数量
        Node x = root;
        while (x != null) {
            if(!isRed(x))
                black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black) {
        if(x == null)
            return black == 0;
        if(!isRed(x))
            black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    /*
    以下与二叉查找树相同
    */

    private int size(Node x) {
        if(x == null)
            return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if(cmp < 0)
                x = x.left;
            else if(cmp > 0)
                x = x.right;
            else
                return x.val;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if(x == null)
            return -1;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public Key min() {
        if(isEmpty())
            throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if(x.left == null)
            return x;
        else
            return min(x.left);
    }

    public Key max() {
        if(isEmpty())
            throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if(x.right == null)
            return x;
        else
            return max(x.right);
    }

    public Key floor(Key key) {
        if(isEmpty())
            throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if(x == null)
            throw new NoSuchElementException("argument to floor() is too small");
        else
            return x.key;
    }

    private Node floor(Node x, Key key) {
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0)
            return x;
        if(cmp < 0)
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if(t != null)
            return t;
        else
            return x;
    }

    public Key ceiling(Key key) {
        if(isEmpty())
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if(x == null)
            throw new NoSuchElementException("argument to ceiling() is too small");
        else
            return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0)
            return x;
        if(cmp > 0)
            return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if(t != null)
            return t;
        else
            return x;
    }

    public Key select(int rank) {
        if(rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    private Key select(Node x, int rank) {
        if(x == null)
            return null;
        int leftSize = size(x.left);
        if(leftSize > rank)
            return select(x.left, rank);
        else if(leftSize < rank)
            return select(x.right, rank - leftSize - 1);
        else
            return x.key;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if(x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return rank(key, x.left);
        else if(cmp > 0)
            return size(x.left) + rank(key, x.right) + 1;
        else
            return size(x.left);
    }

    public Iterable<Key> keys() {
        if(isEmpty())
            return new Queue<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if(x == null)
            return ;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0)
            keys(x.left, queue, lo, hi);
        if(cmplo <= 0 && cmphi >= 0)
            queue.enqueue(x.key);
        if(cmphi > 0)
            keys(x.right, queue, lo, hi);
    }

    public int size(Key lo, Key hi) {
        if(lo.compareTo(hi) > 0)
            return 0;
        if(contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if(x == null)
            return true;
        if(min != null && x.key.compareTo(min) <= 0)
            return false;
        if(max != null && x.key.compareTo(max) >= 0)
            return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if(x == null)
            return true;
        if(x.size != size(x.left) + size(x.right) + 1)
            return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    private boolean isRankConsistent() {
        for(int i = 0; i < size(); i++)
            if(i != rank(select(i)))
                return false;
        for(Key key : keys())
            if(key.compareTo(select(rank(key))) != 0)
                return false;
        return true;
    }
}