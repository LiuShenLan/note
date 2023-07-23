public class RedBlackTree<AnyType extends Comparable<? super AnyType>> {
    /*
    红黑树性质：
    1. 每一个节点或者着成红色，或者着成黑色
    2. 根是黑色的，(null节点都是黑色)
    3. 如果一个节点是红色的，那么它的子节点必须是黑色的
    4. 从一个节点到一个null引用的每一条路径必须包含相同数目的黑色节点
    */
    
    /**
     * Construct the tree.
     */
    public RedBlackTree() {
        nullNode = new RedBlackNode<>(null);
        nullNode.left = nullNode.right = nullNode;
        header = RedBlackNode<>(null);
        header.left = header.right = nullNode;
    }

    /**
     * Compare item and t.element, using CompareTo, with
     * caveat(警告) that if t is header, then item is always larger.
     * This routine(例程) is called if is possible that t is header.
     * If it is not possible for t to be header, use compareTo directly.
     */
    private int compare(AnyType item, RedBlackNode<AnyType> t) {
        if (t == header)
            return 1;
        else
            return item.compareTo(t.element);
    }

    /**
     * Insert into the tree.
     * @param item the item to insert.
     */
    public void insert(AnyType item) {
        current = parent = grand = header;
        nullNode.element = item;

        // 自顶向下
        while (compare(item, current) != 0) {
            great = grand;
            grand = parent;
            parent = current;
            current = compare(item, current) < 0 ? current.left : current.right;

            // Check fi two red children; fix if so
            if (curretn.left.color == RED && current.right.color == RED)
                handleReorient(item);
        }

        // Insertion fails if already present
        if (current != nullNode)
            return ;
        current = new RedBlackNode<>(item, nullNode, nullNode);

        // Attach to parent
        if (compare(item, parent) < 0)
            parent.left = current;
        else
            parent.right = current;
        handleReorient(item);
    }

    /**
     * Remove from the tree.
     * @param x the item to remove.
     * @throws UnsupportedOperationException if called.
     */
    public void remove(AnyType x) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find the smallest item the tree.
     * @return the smallest item or throw UnderflowException if empty.
     */
    public AnyType findMin() {
        if (isEmpty())
            throw new UnderflowException();
        
        RedBlackNode<AnyType> itr = header.right;

        while (itr.left != nullNode)
            itr = itr.left;
        
        return itr.element;
    }

    /**
     * Find the largest item in the tree.
     * @ return the largest item
     */
    public AnyType findMax() {
        if (isEmpty())
            throw new UnderflowException();
        
        RedBlackNode<AnyType> itr = header.right;

        while (itr.right != nullNode)
            itr = itr.right;
        
        return itr.element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found; otherwise false.
     */
    public boolean contains(AnyType x) {
        nullNode.element = x;
        current = header.right;

        for (;;) {
            if (x.compareTo(current.element) < 0)
                current = current.left;
            else if (x.compareTo(current.element) > 0)
                current = current.right;
            else if (current != nullNode)
                return true;
            else
                return false;
        }
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        header.right = nullNode;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(header.right);
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree(RedBlackNode<AnyType> t) {
        if (t != nullNode) {
            printTree(t.left);
            System.out.print(t.element.toString() + ' ');
            printTree(t.right);
        }
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return header.right == nullNode;
    }

    /**
     * Internal routine(例程) thar if called during an insertion.
     * If a node has two red children. Performs flip and rotations.
     * @param item the item being inserted.
     */
    private void handleReorient(AnyType item) {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED) {  // Have to rotate
            grand.color = RED;
            if ((compare(item, grand) < 0) != (compare(item, parent) < 0))  // 之字形，需要两次旋转
                parent = rotate(item, great);   // Start dbl rotate
            current = rotate(item, great);
            current.color = BLACK;
        }
        header.right.color = BLACK; // Make root black
    }

    /**
     * Internal routine that perform a single or double rotation.
     * Because the result is attached to the parent, there are four cases.
     * @param item the parent in handleReorient.
     * @param parent the parent of the root of the rotated subtree.
     * @return the root of the rotated subtree.
     */
    private RedBlackNode<AnyType> rotate(AnyType item, RedBlackNode<AnyType> parent) {
        if (compare(item, parent) < 0)
            return parent.left = compare(item, parent.left) < 0 ?
                rotateWithLeftChild(parent.left) :  // LL
                rotateWithRightChild(parent.left);  // LR
        else
            return parent.right = compare(item, parent.right) < 0 ?
                rotateWithLeftChild(parent.right) : // RL
                rotateWithRightChild(parent.right); // RR
    }

    /**
     * Rotate binary tree node with left child.
     */
    private RedBlackNode<AnyType> rotateWithLeftChild(RedBlackNode<AnyType> k2) {
        RedBlackNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     */
    private RedBlackNode<AnyType> rotateWithRightChild(RedBlackNode<AnyType> k1) {
        RedBlackNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    private static class RedBlackNode<AnyType> {
        // Constructors
        RedBlackNode(AnyType theElement) {
            this(theElement, null, null);
        }

        RedBlackNode(AnyType theElement, RedBlackNode<AnyType> lt, RedBlackNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
            color = RedBlackTree.BLACK;
        }

        AnyType element;    // The data in the node
        RedBlackNode<AnyType> left;     // Left child
        RedBlackNode<AnyType> right;    // Right child
        int color;  // Color
    }

    private RedBlackNode<AnyType> header;
    private RedBlackNode<AnyType> nullNode;

    private static final int BLACK = 1; // Black must be 1
    private static final int RED = 0;

    // Used in insert routine and its helpers
    private RedBlackNode<AnyType> current;
    private RedBlackNode<AnyType> parent;
    private RedBlackNode<AnyType> grand;
    private RedBlackNode<AnyType> great;
}
