package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class LinkedBinaryTree<T> implements BinaryTree<T>, Serializable {

    private BinaryTreeNode<T> root;
    private int height;
    private int size = 0;

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public T root() {
        return (root == null) ? null : root.getElement();
    }

    @Override
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

    @Override
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

    @Override
    public void inOrder() {
        inOrder(this.root);
        System.out.println("");
    }

    public void inOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            System.out.print(node.toString() + " ");
            postOrder(node.getRight());
        }
    }

    @Override
    public void levelOrder() {
        Pila<BinaryTreeNode<T>> pila = new Pila<>();
        pila.push(this.root);
        while (!pila.isEmpty()) {
            BinaryTreeNode<T> temp = (BinaryTreeNode<T>) pila.pop();
            System.out.println(temp + " ");
            if (temp.getLeft() != null) {
                pila.push(temp.getLeft());
            }
            if (temp.getRight() != null) {
                pila.push(temp.getRight());
            }
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
