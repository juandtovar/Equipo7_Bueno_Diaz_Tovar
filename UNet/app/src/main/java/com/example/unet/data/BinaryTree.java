package com.example.unet.data;

public class BinaryTree<T> {

    private BinaryTreeNode<T> root;
    private int height;
    private int size = 0;

    public boolean isEmpty() {
        return this.root == null;
    }

    public T root() {
        return (root == null) ? null : root.getElement();
    }

    public void preOrder() {
        preOrder(this.root);
    }

    public void preOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            System.out.print(node.toString() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void postOrder() {
        postOrder(this.root);
    }

    public void postOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.toString() + " ");
        }
    }

    public void inOrder() {
        inOrder(this.root);
    }

    public void inOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            System.out.print(node.toString() + " ");
            postOrder(node.getRight());
        }
    }

    public int getSize() {
        return getSize(this.root);
    }

    public int getSize(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return getSize(node.getLeft()) + getSize(node.getRight()) + 1;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHeight() {
        return height(this.root);
    }

    public static <T> int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int heightL = height(node.getLeft());
        int heightR = height(node.getRight());
        if (heightL > heightR) {
            return ++heightL;
        } else {
            return ++heightR;
        }
    }

    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<T> root) {
        this.root = root;
    }
}
