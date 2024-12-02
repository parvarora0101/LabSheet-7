package DisasterSystem;
import java.util.*;

public class AVLTree {
    private class Node {
        String data;
        Node left, right;
        int height;

        Node(String data) {
            this.data = data;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    private Node root;

    public AVLTree() {
        root = null;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private Node balance(Node node) {
        int balance = balanceFactor(node);
        if (balance > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node insert(Node node, String data) {
        if (node == null) {
            return new Node(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            return node; // No duplicates
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    public void insert(String data) {
        root = insert(root, data);
    }

    private void inOrder(Node node, List<String> result) {
        if (node != null) {
            inOrder(node.left, result);
            result.add(node.data);
            inOrder(node.right, result);
        }
    }

    public List<String> inOrderTraversal() {
        List<String> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }
}
