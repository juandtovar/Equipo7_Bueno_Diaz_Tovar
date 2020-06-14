package Equipo7_Bueno_Diaz_Tovar.data;

import Equipo7_Bueno_Diaz_Tovar.data.interfaces.BinaryTree;
import java.io.Serializable;

public class LinkedBinaryTree<T> implements BinaryTree<T>, Serializable {

    private LinkedBinaryTreeNode<T> root;

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

    public void preOrder(LinkedBinaryTreeNode<T> node) {
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

    public void postOrder(LinkedBinaryTreeNode<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.toString() + " ");
        }
    }

    @Override
    public void inOrder() {
        inOrder(this.root);
    }

    public void inOrder(LinkedBinaryTreeNode<T> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.toString() + " ");
            inOrder(node.getRight());
        }
    }

    @Override
    public void levelOrder() {
        LinkedQueue<LinkedBinaryTreeNode<T>> queue = new LinkedQueue<>();
        queue.put(this.root);
        LinkedBinaryTreeNode<T> node;
        while (!queue.isEmpty()) {
            node = (LinkedBinaryTreeNode<T>) queue.remove();
            System.out.print(node + " ");
            if (node.getLeft() != null) {
                queue.put(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.put(node.getRight());
            }
        }
    }

    public int getSize() {
        return getSize(this.root);
    }

    public int getSize(LinkedBinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return getSize(node.getLeft()) + getSize(node.getRight()) + 1;
    }

    public int getHeight() {
        return height(this.root);
    }

    public int height(LinkedBinaryTreeNode<T> node) {
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

    public LinkedBinaryTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(LinkedBinaryTreeNode<T> root) {
        this.root = root;
    }
}
