package homework;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<V extends Comparable<V>>{
    private Node root;

    /**
     * Добавление значения в дерево
     *
     * @param value значение для добавления
     */
    public boolean add(V value) {
        Node newNode = new Node();
        newNode.value = value;
        if (root == null) {
            root = newNode;
            root.color = Color.BLACK;
            return true;
        } else {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        }
    }

    /**
     * Добавление ноды
     *
     * @param node
     * @param value
     * @return
     */
    private boolean addNode(Node node, V value) {
        if (node.value.compareTo(value) == 0) {
            return false;
        } else {
            if (node.value.compareTo(value) > 0) {
                if (node.left != null) {
                    boolean result = addNode(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                } else {
                    node.left = new Node();
                    node.left.color = Color.RED;
                    node.left.value = value;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean result = addNode(node.right, value);
                    node.right = rebalance(node.right);
                    return result;
                } else {
                    node.right = new Node();
                    node.right.color = Color.RED;
                    node.right.value = value;
                    return true;
                }
            }
        }
    }

    /**
     * Проверка содержится ли значение в дереве
     *
     * @param value значение для поиска
     * @return true - если элемент найден, false - если совпадений не нашлось
     */
    public boolean contains(V value) {
        Node node = root;
        while (node != null) {
            if (node.value.equals(value)) {
                return true;
            }
            if (node.value.compareTo(value) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    /**
     * Ребалансировка дерева
     *
     * @param node
     * @return
     */
    private Node rebalance(Node node){
        Node result = node;
        boolean needRebalance;
        do{
            needRebalance = false;
            if(result.right != null && result.right.color == Color.RED &&
                    (result.left == null || result.left.color == Color.BLACK)){
                needRebalance = true;
                result = rightSwap(result);
            }
            if(result.left != null && result.left.color == Color.RED &&
                    result.left.left != null && result.left.left.color == Color.RED){
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED &&
                    result.right != null && result.right.color == Color.RED){
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }

    /**
     * Правый поворот
     * @param node
     * @return
     */
    private Node rightSwap(Node node){
        Node rightChild = node.right;
        Node betweenChild = rightChild.left;
        rightChild.left = node;
        node.right = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    /**
     * Девый поворот
     * @param node
     * @return
     */
    private Node leftSwap(Node node){
        Node leftChild = node.left;
        Node betweenChild = leftChild.right;
        leftChild.right = node;
        node.left = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    /**
     * Смена цвета
     * @param node
     */
    private void colorSwap(Node node){
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }

    /**
     * Общее количество нод в дереве
     * @return
     */
    public int counter() {
        int counter = 0;
        if (root != null) {
            List<Node> line = new ArrayList<>();
            line.add(root);
            while (line.size() > 0) {
                List<Node> nextLine = new ArrayList<>();
                for (Node node : line) {
                    if (node != null) {
                        counter++;
                    }
                    if (node.left != null) nextLine.add(node.left);
                    if (node.right != null) nextLine.add(node.right);
                }
                line = nextLine;
            }
            return counter;
        }
        return -1;
    }

    private class Node {
        private V value;
        private Node left;
        private Node right;
        private Color color;
    }

    enum Color {
        RED, BLACK
    }
}
