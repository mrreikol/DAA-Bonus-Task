package bst;

public class BST {
    private BSTNode root;

    public BST() {
        this.root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private BSTNode insertRec(BSTNode current, int key) {
        if (current == null) {
            return new BSTNode(key);
        }
        if (key < current.key) {
            current.left = insertRec(current.left, key);
        } else if (key > current.key) {
            current.right = insertRec(current.right, key);
        }
        return current;
    }

    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(BSTNode current, int key) {
        if (current == null) {
            return false;
        }
        if (current.key == key) {
            return true;
        }
        return key < current.key ? searchRec(current.left, key) : searchRec(current.right, key);
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private BSTNode deleteRec(BSTNode current, int key) {
        if (current == null) {
            return null;
        }

        if (key < current.key) {
            current.left = deleteRec(current.left, key);
        } else if (key > current.key) {
            current.right = deleteRec(current.right, key);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            current.key = findMinValue(current.right);
            current.right = deleteRec(current.right, current.key);
        }
        return current;
    }

    private int findMinValue(BSTNode node) {
        int minVal = node.key;
        while (node.left != null) {
            minVal = node.left.key;
            node = node.left;
        }
        return minVal;
    }
}