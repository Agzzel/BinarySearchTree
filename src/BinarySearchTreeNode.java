public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;


    public BinarySearchTreeNode(T data) {
        this.data = data;
    }

    public boolean add(T data) {
        int compareToResult = data.compareTo(this.data); // -1,0,1.

        if (compareToResult < 0) {
            if (left == null) {
                left = new BinarySearchTreeNode<T>(data);
                return true;
            } else {
                return left.add(data);
            }
        }
        if (compareToResult > 0) {
            if (right == null) {
                right = new BinarySearchTreeNode<T>(data);
                return true;
            } else {
                return right.add(data);
            }
        }
        return false;
    }

    private T findMin() {
        if (left == null) {
            return this.data;
        }
        return left.findMin();
    }

    public BinarySearchTreeNode<T> remove(T data) { // SKICKA MED ROOT NODEN, INTE DET SOM TAS BORT.

        int compareResult = data.compareTo(this.data);

        if (compareResult == 0) {
            if (left == null && right == null) { // Är också ett löv.
                return null;
            }
            if (left != null && right == null) { //Found a child to the left node
                return left;
            }
            if (left == null){ // one child
                return right;
            }
            //Now we know there are two children
            this.data = right.findMin();
            this.right = right.remove(this.data);

        } else if (left != null && compareResult < 0) {
            left = left.remove(data);
        } else if (right != null) {
            right = right.remove(data);
        }
        return this;
    }

    public boolean contains(T data) {
        int compareToResult = data.compareTo(this.data);
        if (compareToResult == 0) {
            return true;
        }
        // Check so that the data is not null
        if (right != null && compareToResult > 0) {
            return right.contains(data);
        }

        if (left != null && compareToResult < 0) {
            return left.contains(data);
        }
        return false;
    }

    public int size() {
        return (left == null ? 0 : left.size()) + 1 + (right == null ? 0 : right.size());
    }


    public int depth() {// The left side is compared to the right side
        int l = -1;
        int r = -1;
        if (left != null) {
            l = left.depth();
        }
        if (right != null) {
            r = right.depth();
        }
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    public String toString() {
        String result = "";
        if (left != null) {
            result = result + left + ", ";
        }
        result = result + data;

        if (right != null) {
            result = result + ", " + right;
        }
        return result;

    }
}
