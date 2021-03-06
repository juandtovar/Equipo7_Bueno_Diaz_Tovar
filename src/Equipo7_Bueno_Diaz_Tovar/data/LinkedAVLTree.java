package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class LinkedAVLTree<T extends Comparable<T>> extends LinkedBinarySearchTree<T> implements Serializable {

    public LinkedAVLTree() {

    }

    private LinkedBinaryTreeNode simpleRotationLeft(LinkedBinaryTreeNode x) {
        LinkedBinaryTreeNode y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), x.getHeight()) + 1);
        return y;
    }

    private LinkedBinaryTreeNode simpleRotationRight(LinkedBinaryTreeNode x) {
        LinkedBinaryTreeNode y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        x.setHeight(Math.max(height(x.getLeft()),
                height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getRight()),
                x.getHeight()) + 1);
        return y;
    }

    private LinkedBinaryTreeNode doubleRotationLeft(LinkedBinaryTreeNode x) {
        x.setLeft(simpleRotationRight(x.getLeft()));
        return simpleRotationLeft(x);
    }

    private LinkedBinaryTreeNode doubleRotationRight(LinkedBinaryTreeNode x) {
        x.setRight(simpleRotationLeft(x.getRight()));
        return simpleRotationRight(x);
    }

    private LinkedBinaryTreeNode balance(LinkedBinaryTreeNode node) {
        if (node == null) {
            return node;
        }
        if (height(node.getLeft()) - height(node.getRight()) > 1) {
            if (height(node.getLeft().getLeft())
                    >= height(node.getLeft().getRight())) {
                node = simpleRotationLeft(node);
            } else {
                node = doubleRotationLeft(node);
            }
        } else if (height(node.getRight()) - height(node.getLeft()) > 1) {
            if (height(node.getRight().getRight())
                    >= height(node.getRight().getLeft())) {
                node = simpleRotationRight(node);
            } else {
                node = doubleRotationRight(node);
            }
        }
        node.setHeight(Math.max(height(node.getLeft()),
                height(node.getRight())) + 1);
        return node;
    }

    public void add(T element) {
        setRoot(add(getRoot(), element));
    }

    private LinkedBinaryTreeNode<T> add(LinkedBinaryTreeNode<T> node, T element) {
        if (node == null) {
            this.setSize(this.getSize() + 1);
            return new LinkedBinaryTreeNode<>(element);
        }
        int comparation = element.compareTo(node.getElement());
        if (comparation < 0) {
            node.setLeft(add(node.getLeft(), element));
        } else if (comparation > 0) {
            node.setRight(add(node.getRight(), element));
        }
        return balance(node);
    }

    public void remove(T element) {
        setRoot(remove(getRoot(), element));
    }

    private LinkedBinaryTreeNode<T> remove(LinkedBinaryTreeNode<T> node, T element) {
        if (node == null) {
            return null;
        }
        int comparation = element.compareTo(node.getElement());
        if (comparation < 0) {
            node.setLeft(remove(node.getLeft(), element));
        } else if (comparation > 0) {
            node.setRight(remove(node.getRight(), element));
        } else {
            if (node.getLeft() != null && node.getRight() != null) {
                node.setElement(getMin(node.getRight()));
                node.setRight(remove(node.getRight(), node.getElement()));
            } else {
                node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                setSize(getSize() - 1);
            }
        }
        return balance(node);
    }
}
