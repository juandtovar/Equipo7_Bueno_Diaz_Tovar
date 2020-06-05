package com.example.unet.data;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public AVLTree() {

    }

    private BinaryTreeNode simpleRotationLeft(BinaryTreeNode x) {
        BinaryTreeNode y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), x.getHeight()) + 1);
        return y;
    }

    private BinaryTreeNode simpleRotationRight(BinaryTreeNode x) {
        BinaryTreeNode y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        x.setHeight(Math.max(height(x.getLeft()),
                height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getRight()),
                x.getHeight()) + 1);
        return y;
    }

    private BinaryTreeNode doubleRotationLeft(BinaryTreeNode x) {
        x.setLeft(simpleRotationRight(x.getLeft()));
        return simpleRotationLeft(x);
    }

    private BinaryTreeNode doubleRotationRight(BinaryTreeNode x) {
        x.setRight(simpleRotationLeft(x.getRight()));
        return simpleRotationRight(x);
    }

    private BinaryTreeNode balance(BinaryTreeNode node) {
        if (node == null) {
            return node;
        }
        if (height(node.getLeft()) - height(node.getRight()) > 1) {
            if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
                node = simpleRotationLeft(node);
            } else {
                node = doubleRotationLeft(node);
            }
        } else if (height(node.getRight()) - height(node.getLeft()) > 1) {

            if (height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
                node = simpleRotationRight(node);
            } else {
                node = doubleRotationRight(node);
            }
        }

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        return node;
    }

    public void add(T element) {
        setRoot(add(getRoot(), element));
    }

    private BinaryTreeNode<T> add(BinaryTreeNode<T> nodo, T element) {
        if (nodo == null) {
            this.setSize(this.getSize() + 1);
            return new BinaryTreeNode<T>(element);
        }
        int comparation = element.compareTo(nodo.getElement());
        if (comparation < 0) {
            nodo.setLeft(add(nodo.getLeft(), element));
        } else if (comparation > 0) {
            nodo.setRight(add(nodo.getRight(), element));
        }
        return balance(nodo);
    }

    public void remove(T x) {
        setRoot(remove(getRoot(), x));
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T x) {
        if (node == null) return null;
        int comparation = x.compareTo(node.getElement());
        if (comparation < 0)
            node.setLeft(remove(node.getLeft(), x));
        else if (comparation > 0)
            node.setRight(remove(node.getRight(), x));
        else {
            if (node.getLeft() != null && node.getRight() != null) {
                node.setElement(getMin(node.getRight()));
                node.setRight(remove(node.getRight(), node.getElement()));
            } else {
                node = (node.getLeft() != null) ? node.getLeft() :
                        node.getRight();
                setSize(getSize() - 1);
            }
        }
        return balance(node);
    }
}
