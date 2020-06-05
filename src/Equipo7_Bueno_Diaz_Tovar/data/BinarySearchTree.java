package Equipo7_Bueno_Diaz_Tovar.data;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinaryTreeNode<T> find(T element) {
        BinaryTreeNode<T> temp = getRoot();
        while (temp != null) {
            int com = element.compareTo(temp.getElement());
            if (com == 0) {
                return temp;
            } else if (com > 0) {
                temp = temp.getRight();
            } else {
                temp = temp.getLeft();
            }
        }
        return null;
    }

    public boolean contains(T element) {
        BinaryTreeNode<T> temp = getRoot();
        while (temp != null) {
            int com = element.compareTo(temp.getElement());
            if (com == 0) {
                return true;
            }
            if (com > 0) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return false;
    }

    public T getMin() {
        return getMin(getRoot());
    }

    public T getMax() {
        return getMax(getRoot());
    }

    public T getMin(BinaryTreeNode<T> nodeInicial) {
        BinaryTreeNode<T> node = nodeInicial;
        T minElement = null;
        while (node != null) {
            minElement = node.getElement();
            node = node.getLeft();
        }
        return minElement;
    }

    public T getMax(BinaryTreeNode<T> nodeInicial) {
        BinaryTreeNode<T> node = nodeInicial;
        T maxElement = null;
        while (node != null) {
            maxElement = node.getElement();
            node = node.getRight();
        }
        return maxElement;
    }

}
