// 二项队列，有二项树构成的集合(森林)，高度为0的二项树儿单节点树，高度为k的树为将一棵高度为k-1的树附加到另一颗高度为k-1的树上
public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {
    public BinomialQueue() {
        theTrees = new BinomialQueueNode[DEFAULT_TREES];
        makeEmpty();
    }

    public BinomialQueue(AnyType item) {
        currentSize = 1;
        theTrees = new BinomialQueueNode[1];
        theTrees[0] = new BinomialQueueNode<>(item, null, null);
    }

    /**
     * Merge rhs into the priority queue.
     * rhs become empty. rhs must be different from this.
     * @param rhs the other binomial queue.
     */
    public void merge(BinomialQueue<AnyType> rhs) {
        if (this == rhs)    // Avoid aliasing problems
            return;
        
        currentSize += rhs.currentSize;

        if (currentSize > capacity()) {
            int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
            expandTheTrees(maxLength + 1);
        }

        BinomialQueueNode<AnyType> carry = null;
        for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            BinomialQueueNode<AnyType> t1 = theTrees[i];
            BinomialQueueNode<AnyType> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch (whichCase) {
                case 0: /*No trees */
                case 1: /* Only this */
                    break;
                case 2: /* Only rhs */
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 4: /* Only carry */
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 3: /* this and rhs */
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 5: /* this and carry */
                    carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                case 6: /* rhs and carry */
                    carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7: /* All tree */
                    theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }

            for (int k = 0; k < rhs.theTrees.length; k++)
                rhs.theTrees[k] = null;
            rhs.currentSize = 0;
        }
    }
    
    public void insert(AnyType x) {
        merge(new BinomialQueue<>(x));
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType findMin() {
        if (isEmpty())
            throw new UnderflowException();
        
        return theTrees[findMinIndex()].element;
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw UnderflowException if empty.
     */
    public AnyType deleteMin() {
        if (isEmpty())
            throw new UnderflowException();
        
        int minIndex = findMinIndex();
        AnyType minItem = theTrees[minIndex].element;

        BinomialQueueNode<AnyType> deletedTree = theTrees[minIndex].leftChild;

        // Construct H''
        BinomialQueue<AnyType> deletedQueue = new BinomialQueue<>();
        deletedQueue.expandTheTrees(minIndex + 1);

        deletedQueue.currentSize = (1 << minIndex) - 1;
        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }

        // Construct H'
        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;
        
        merge(deletedQueue);

        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < theTrees.length; i++)
            theTrees[i] = null;
    }

    private static class BinomialQueueNode<AnyType> {
        //Constructors
        BinomialQueueNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinomialQueueNode(AnyType theElement, BinomialQueueNode<AnyType> lt, BinomialQueueNode<AnyType> nt) {
            element = theElement;
            leftChild = lt;
            nextSibling = nt;
        }

        AnyType element;    // The data in the node
        BinomialQueueNode<AnyType> leftChild;   // Left child
        BinomialQueueNode<AnyType> nextSibling; // Right child  (右兄弟?)
    }

    private static final int DEFAULT_TREES = 1;

    private int currentSize;
    private BinomialQueueNode<AnyType>[] theTrees;

    private void expandTheTrees(int newNumTrees) {
        BinomialQueueNode<AnyType>[] old = theTrees;
        int oldNumTrees = theTrees.length;

        theTrees = new BinomialQueueNode[newNumTrees];
        for (int i = 0; i < Math.min(oldNumTrees, newNumTrees); i++)
            theTrees[i] = old[i];
        for (int i = oldNumTrees; i < newNumTrees; i++)
            theTrees[i] = null;
    }

    /**
     * Return the result of merging equal-sized t1 and t2.
     */
    private BinomialQueueNode<AnyType> combineTrees(BinomialQueueNode<AnyType> t1, BinomialQueueNode<AnyType> t2) {
        if (t1.element.compareTo(t2.element) > 0)
            return combineTrees(t2, t1)
        
        // t1.element < t2.element
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    private int capacity() {
        return (1 << theTrees.length) - 1;
    }

    /**
     * Find index of tree containing the smallest item in the priority queue.
     * The priority queue must not be empty.
     * @return the index of tree containing the smallest item.
     */
    private int findMinIndex() {
        int i;
        int minIndex;

        for (int i = 0; theTrees[i] == null; i++)
            ;
        
        for (minIndex = i; i < theTrees.length; i++)
            if (theTrees[i] != null && theTrees[i].element.compareTo(theTrees[minIndex].element) < 0)
                minIndex = i;
        
        return minIndex;
    }
}