import java.io.Serializable;
import java.util.ArrayList;

public class BST<T> implements Serializable {
    Node<T> root;
    MyComparator<Integer> comparator = new MyComparator<Integer>();

    public BST() {
        root = null;
    }

    public Node<T> getMinimum(Node<T> fromThis) {
        Node<T> minimumNode = fromThis;

        while (minimumNode.getLeft() != null)
            minimumNode = minimumNode.getLeft();

        return minimumNode;
    }

    public Node<T> getMaximum(Node<T> fromThis) {
        Node<T> maximumNode = fromThis;

        while (maximumNode.getRight() != null)
            maximumNode = maximumNode.getRight();

        return maximumNode;
    }

    public void addNode(int value) {
        Node<T> addThis = new Node(value);

        if (contains(value)) {
            System.out.println(value + " - tree contains this object already.");
        } 
        else if (root == null) root = addThis;
        
        else {
            Node<T> actual = root;
            Node<T> parent = null;
            
            while (actual != null) {
                parent = actual;
                if (comparator.compare(value, actual.getValue()) < 0) {
                    actual = actual.getLeft();
                } else {
                    actual = actual.getRight();
                }
            }
            
            if (comparator.compare(value, parent.getValue()) < 0) {
                parent.setLeft(addThis);
                parent.getLeft().setParent(parent);
            } else {
                parent.setRight(addThis);
                parent.getRight().setParent(parent);
            }
        }
    }

    public Node<T> search(Integer value) throws MyException {
        Node<T> actual = root;
        while ((actual.getLeft() != null || actual.getRight() != null)&&(actual != null && comparator.compare(actual.getValue(), value) != 0)) {
            if(comparator.compare(actual.getValue(), value) > 0){
                actual = actual.getLeft();
            }else if(comparator.compare(actual.getValue(), value) < 0){
                actual = actual.getRight();
            }
        }
        if (actual == null)
            throw new MyException("Value not found");
        return actual;
    }

    public Node<T> getPredecessor(Integer value) throws MyException {
        Node<T> node = this.search(value);
        if (node.getLeft() != null) {
            node = node.getLeft();
            while (node.getRight() != null)
                node = node.getRight();
            return node;
        } else if (node.getLeft() == null && node != root && node != this.getMinimum(root)) {
            Node<T> parent = node.getParent();
            while (parent != root && comparator.compare(parent.getValue(), node.getValue()) > 0)
                parent = parent.getParent();
            return parent;
        } else
            throw new MyException("Predecessor not found.");
    }

    public Node<T> getSuccessor(Integer value) throws MyException {
        Node<T> node = this.search(value);

        
        if (node.getRight() != null) {
            node = node.getRight();
            while (node.getLeft() != null)
                node = node.getLeft();
            return node;
        } else if (node.getRight() == null && node != root && node != this.getMaximum(root)) {
            Node<T> parent = node.getParent();
            while (parent != root && comparator.compare(parent.getValue(), node.getValue()) < 0)
                parent = parent.getParent();
            return parent;
        } else
            throw new MyException("Successor not found.");
    }

    public boolean contains(int value) {
        Node<T> actual = root;
        while (actual != null && actual.getValue() != value)
            actual = (comparator.compare(actual.getValue(), value) > 0) ? actual.left : actual.right;
        return actual != null;
    }

    public void inOrder(Node<T> node, ArrayList<Integer> list) {
        if (node != null) {
            inOrder(node.getLeft(), list);
            list.add(node.getValue());
            inOrder(node.getRight(), list);
        }
    }

    public void preOrder(Node<T> node, ArrayList<Integer> list) {
        if (node != null) {
            list.add(node.getValue());
            preOrder(node.getLeft(), list);
            preOrder(node.getRight(), list);
        }
    }

    public void postOrder(Node<T> node, ArrayList<Integer> list) {
        if (node != null) {
            postOrder(node.getLeft(), list);
            postOrder(node.getRight(), list);
            list.add(node.getValue());
        }
    }

    public int countHeight(Node<T> node) {
        if (node == null) return -1;
        node.setHeight(1 + Math.max(countHeight(node.getLeft()), countHeight(node.getRight())));
        return node.getHeight();
    }

    public int countNodes(Node<T> node) {
        if (node == null) return 0;
        node.setNodes(1 + countNodes(node.getLeft()) + countNodes(node.getRight()));
        return node.getNodes();
    }

    public int countLeaves(Node<T> node) {
        if (node == null) return 0;

        else if (node.getLeft() == null && node.getRight() == null) {
            node.setLeaves(1);
            return node.getLeaves();
        }

        node.setLeaves(countLeaves(node.getLeft()) + countLeaves(node.getRight()));

        return node.getLeaves();
    }

    public void removeNode(int value){
        root = delete_Recursive(root, value);
    }

    Node delete_Recursive(Node root, int value) {

        if (root == null) return root;

        if (value < root.getValue())
            root.setLeft(delete_Recursive(root.getLeft(), value));

        else if (value > root.getValue())
            root.setRight(delete_Recursive(root.getRight(), value));

        else {
            if (root.getLeft() == null)
                return root.getRight();
            else if (root.getRight() == null)
                return root.getLeft();

            root.setValue(getMinimum(root.getRight()).getValue());

            root.setRight(delete_Recursive(root.getRight(), root.getValue()));
        }

        return root;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> newRoot){ root = newRoot;}

}
