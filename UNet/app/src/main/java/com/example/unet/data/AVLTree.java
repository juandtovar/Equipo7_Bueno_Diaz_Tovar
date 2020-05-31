package com.example.unet.data;

public class AVLTree {

    private AVLTreeNode root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void inOrder() {
        theInOrder(this.root);
    }

    public static void theInOrder(AVLTreeNode t) {
        if (t != null) {
            theInOrder(t.getLeft());
            System.out.printf("%d %d %d\n", t.getCodigo(), t.getSemestre(), t.getPosición());
            theInOrder(t.getRight());
        }
    }

    public AVLTreeNode find(int codigo) {
        AVLTreeNode temp = root;
        while (temp != null) {
            if (temp.getCodigo() == codigo) {
                return temp;
            } else if (temp.getCodigo() < codigo) {
                temp = temp.getRight();
            } else {
                temp = temp.getLeft();
            }
        }
        return null;
    }

    public boolean contains(int x) {
        AVLTreeNode temp = root;
        while (temp != null) {
            if (x == temp.getCodigo()) {
                return true;
            }
            if (x < temp.getCodigo()) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return false;
    }

    public void add(int codigo, int semestre, int pos) {
        root = add(root, codigo, semestre, pos);
    }

    private AVLTreeNode add(AVLTreeNode nodo, int codigo, int semestre, int pos) {
        if (nodo == null) {
            size++;
            return new AVLTreeNode(codigo, semestre, pos);
        }
        if(nodo.getCodigo()==codigo){
            return null;
        }
        if (codigo < nodo.getCodigo()) {
            nodo.setLeft(add(nodo.getLeft(), codigo, semestre, pos));
        } else if (codigo > nodo.getCodigo()) {
            nodo.setRight(add(nodo.getRight(), codigo, semestre, pos));
        }
        return balance(nodo);
    }

    private AVLTreeNode balance(AVLTreeNode nodo) {
        if (nodo == null) {
            return nodo;
        }
        if (height(nodo.getLeft()) - height(nodo.getRight()) > 1) {
            if (height(nodo.getLeft().getLeft()) >= height(nodo.getLeft().getRight())) {
                nodo = simpleRotationLeft(nodo);
            } else {
                nodo = doubleRotationLeft(nodo);
            }
        } else if (height(nodo.getRight()) - height(nodo.getLeft()) > 1) {

            if (height(nodo.getRight().getRight())
                    >= height(nodo.getRight().getLeft())) {

                nodo = simpleRotationRight(nodo);
            } else {
                nodo = doubleRotationRight(nodo);
            }
        }

        nodo.setHeight(Math.max(height(nodo.getLeft()), height(nodo.getRight())) + 1);
        return nodo;
    }

    public void remove(int x) {
        root = remove(root, x);
    }

    private AVLTreeNode remove(AVLTreeNode node, int x) {
        if (node == null) {
            return null;
        }
        if (x < node.getCodigo()) {
            node.setLeft(remove(node.getLeft(), x));
        } else if (x > node.getCodigo()) {
            node.setRight(remove(node.getRight(), x));
        } else {
            if (node.getLeft() != null && node.getRight() != null) {
                node.setCodigo(getMin(node.getRight()));
                node.setRight(remove(node.getRight(), node.getCodigo()));
            } else {
                node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                size--;
            }
        }
        return balance(node);
    }

    private AVLTreeNode simpleRotationLeft(AVLTreeNode x) {
        AVLTreeNode y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), x.getHeight()) + 1);
        return y;
    }

    private AVLTreeNode simpleRotationRight(AVLTreeNode x) {
        AVLTreeNode y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        x.setHeight(Math.max(height(x.getLeft()),
                height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getRight()),
                x.getHeight()) + 1);
        return y;
    }

    private AVLTreeNode doubleRotationLeft(AVLTreeNode x) {
        x.setLeft(simpleRotationRight(x.getLeft()));
        return simpleRotationLeft(x);
    }

    private AVLTreeNode doubleRotationRight(AVLTreeNode x) {
        x.setRight(simpleRotationLeft(x.getRight()));
        return simpleRotationRight(x);
    }

    public int getMin(AVLTreeNode x) {
        AVLTreeNode y = x;
        int min = x.getCodigo();
        while (y != null) {
            min = y.getCodigo();
            y = y.getLeft();
        }
        return min;
    }

    public int getMin() {
        return getMin(this.root);
    }

    public int getMax(AVLTreeNode x) {
        AVLTreeNode y = x;
        int max = x.getCodigo();
        while (y != null) {
            max = y.getCodigo();
            y = y.getRight();
        }
        return max;
    }

    public int getMax() {
        return getMax(this.root);
    }

    private int maxHeight(AVLTreeNode a, AVLTreeNode b) {
        int ha;
        int hb;
        if (a == null) {
            ha = -1;
        } else {
            ha = a.getHeight();
        }
        if (b == null) {
            hb = -1;
        } else {
            hb = b.getHeight();
        }
        if (ha == hb) {
            return -1;
        } else if (ha > hb) {
            return ha;
        } else {
            return hb;
        }
    }

    private int height(AVLTreeNode x) {
        int ha;
        if (x == null) {
            return -1;
        } else {
            return 1 + Math.max(height(x.getLeft()), height(x.getRight()));
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
