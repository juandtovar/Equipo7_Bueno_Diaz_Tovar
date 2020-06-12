package Equipo7_Bueno_Diaz_Tovar.data;

import java.io.Serializable;

public class LinkedBinaryTreeNode<T> implements Serializable {

    private T element;
    private int height;
    private LinkedBinaryTreeNode<T> left;
    private LinkedBinaryTreeNode<T> right;
    private LinkedBinaryTreeNode<T> parent;

    public LinkedBinaryTreeNode() {

    }

    public LinkedBinaryTreeNode(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LinkedBinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(LinkedBinaryTreeNode<T> left) {
        this.left = left;
    }

    public LinkedBinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(LinkedBinaryTreeNode<T> right) {
        this.right = right;
    }

    public LinkedBinaryTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(LinkedBinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return element.toString();
    }

}
