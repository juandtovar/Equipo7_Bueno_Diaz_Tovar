package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class LinkedBinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> implements Serializable {

    private int size;

    public LinkedBinarySearchTree() {
        super();
        this.size = 0;
    }

    @Override
    public int getSize() {
        return super.getSize();
    }

    public LinkedBinaryTreeNode<T> find(T element) {
        LinkedBinaryTreeNode<T> temp = getRoot();
        int com;
        while (temp != null) {
            com = element.compareTo(temp.getElement());
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
        LinkedBinaryTreeNode<T> temp = getRoot();
        int com;
        while (temp != null) {
            com = element.compareTo(temp.getElement());
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

    public T getMin(LinkedBinaryTreeNode<T> nodeInicial) {
        LinkedBinaryTreeNode<T> node = nodeInicial;
        T minElement = null;
        while (node != null) {
            minElement = node.getElement();
            node = node.getLeft();
        }
        return minElement;
    }

    public T getMax() {
        return getMax(getRoot());
    }

    public T getMax(LinkedBinaryTreeNode<T> nodeInicial) {
        LinkedBinaryTreeNode<T> node = nodeInicial;
        T maxElement = null;
        while (node != null) {
            maxElement = node.getElement();
            node = node.getRight();
        }
        return maxElement;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
