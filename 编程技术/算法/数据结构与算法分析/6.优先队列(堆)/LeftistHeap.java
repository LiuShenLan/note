public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {
    public LeftistHeap() {
        root = null;
    }

    /**
     * Merge rhs into the priority queue.
     * rhs become empty. rhs must be different from this.
     * @param rhs the other leftist heap.
     */
    public void merge(LeftistHeap<AnyType> rhs) {
        if (this == rhs)    // Avoid aliasing problems
            return ;
        
        root = merge(root, rhs.root);
        rhs.root = null;
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = merge(new LeftistHeapNode<>(x), root)
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType findMin() {
        if (isEmpty())
            throw new UnderflowException();
        return root.element;
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType deleteMin() {
        if (isEmpty())
            throw new UnderflowException();
        
        AnyType minItem = root.element;
        root = merge(root.left, root.right);

        return minItem;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void makeEmpty() {
        root = null;
    }

    private static class LeftistHeapNode<AnyType> {
        // Constructors
        LeftistHeapNode(AnyType theElement) {
            this(theElement, null, null);
        }

        LeftistHeapNode(AnyType theElement, LeftistHeapNode<AnyType> lt, LeftistHeapNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
            npl = 0;
        }

        AnyType element;    // The data in the node
        LeftistHeapNode<AnyType> left;  // Left child
        LeftistHeapNode<AnyType> right; // Right child
        int npl;    // null path length
    }

    private LeftistHeapNode<AnyType> root;  // root

    /**
     * Internal method to merge two roots.
     * Deals with deviant cases and calls recursive merge1.
     */
    private LeftistHeapNode<AnyType> merge(LeftistHeapNode<AnyType> h1, LeftistHeapNode<AnyType> h2) {
        if (h1 == null)
            return h2;
        if (h2 == null)
            return h1;
        if (h1.element.compareTo(h2.element) < 0)
            return merge1(h1, h2);
        else
            return merge1(h2, h1);
    }

    /**
     * Internal method to merge two roots.
     * Assumes trees are not empty, and h1's root contains smallest item.
     */
    private LeftistHeapNode<AnyType> merge1(LeftistHeapNode<AnyType> h1, LeftistHeapNode<AnyType> h2) {
        if (h1.left == null)
            h1.left = h2;
        else {
            h1.right = merge(h1.right, h2);
            if (h1.left.npl < h1.right.npl)
                swapChildren(h1);
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    /**
     * Swaps t`s two children.
     */
    private void swapChildren(LeftistHeapNode<AnyType> t) {
        LeftistHeapNode<AnyType> tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }
}