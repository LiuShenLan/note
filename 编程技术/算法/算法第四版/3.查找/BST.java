public class BST<Key extends Comparable<Key>, Value> {
    private Node root;  // 二叉查找树的根节点

    private class Node {
        private Key key;    // 键
        private Value val;  // 值
        private Node left, right;   // 指向子树的链接
        private int size;   // 以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if(x == null)
            return 0;
        else
            return x.size;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // 在以x为根节点的子树中查找并返回key所对应的值
        // 如果找不到则返回null
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return get(x.left, key);
        else if(cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public void put(Key key, Value val) {
        // 查找key，找到则更新它的值，否则为他创建一个新的节点
        if(val == null) {
            delete(key);
            return ;
        }

        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // 如果key存在于以x为根节点的子树中则更新它的值
        // 否则将以key和val为键值对的新节点插入到该子树中
        if(x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = put(x.left,  key, val);
        else if(cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        if(isEmpty())
            throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if(x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if(isEmpty())
            throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if(x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if(key == null)
            throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if(x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = delete(x.left, key);
        else if(cmp > 0)
            x.right = delete(x.right, key);
        else {
            if(x.right == null)
                return x.left;
            if(x.left  == null)
                return x.right;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        if(isEmpty())
            throw new NoSuchElementException("calls min() with empty symbol table");
        return
            min(root).key;
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
        return
            max(root).key;
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
        if(cmp <  0)   // 给定键<二叉树根节点
            return floor(x.left, key);  // floor()在根节点左子树中
        Node t = floor(x.right, key);   // 给定键>=二叉树根节点
        if(t != null)
            return t;
        else
            return x;
    }

    public Key floor2(Key key) {
        Key x = floor2(root, key, null);
        if(x == null)
            throw new NoSuchElementException("argument to floor() is too small");
        else return x;

    }

    private Key floor2(Node x, Key key, Key best) {
        if(x == null)
            return best;
        int cmp = key.compareTo(x.key);
        if(cmp  < 0)
            return floor2(x.left, key, best);
        else if(cmp  > 0)
            return floor2(x.right, key, x.key);
        else
            return x.key;
    }

    public Key ceiling(Key key) {
        if(isEmpty())
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if(x == null)
            throw new NoSuchElementException("argument to floor() is too large");
        else
            return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0)
            return x;
        if(cmp < 0) {
            Node t = ceiling(x.left, key);
            if(t != null)
                return t;
            else
                return x;
        }
        return ceiling(x.right, key);
    }

    // 返回排名为rank的键，即树中正好有rank个小于它的键
    public Key select(int rank) {
        if(rank < 0 || rank >= size())
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    // Precondition: rank is in legal range.
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

    // 返回键key的排名，即树中有多少小于键key的键
    public int rank(Key key) {
        return rank(key, root);
    }

    // 返回以x为根节点的子树中小于x.key的键的数量
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

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if(x == null)
            return -1;
        return Math.max(height(x.left), height(x.right)) + 1;
    }

    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if(x == null)
                continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
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
