package com.example.unet.data;

public class AVLTreeNode {

    private int codigo;
    private int semestre;
    private int posición;
    private int height;
    private AVLTreeNode left;
    private AVLTreeNode right;
    private AVLTreeNode parent;

    public AVLTreeNode() {

    }

    public AVLTreeNode(int codigo, int semestre, int posición) {
        this.codigo = codigo;
        this.semestre = semestre;
        this.posición = posición;
    }

    public AVLTreeNode(int x) {
        this.codigo = x;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getPosición() {
        return posición;
    }

    public void setPosición(int posición) {
        this.posición = posición;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
    }

    public AVLTreeNode getParent() {
        return parent;
    }

    public void setParent(AVLTreeNode parent) {
        this.parent = parent;
    }

}
