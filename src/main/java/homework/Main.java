package homework;

public class Main {
    public static void main(String[] args) {
        RedBlackTree <Integer> tree = new RedBlackTree<>();
        System.out.println(tree.add(7));
        System.out.println(tree.add(4));
        System.out.println(tree.add(5));
        System.out.println(tree.add(5));
        System.out.println(tree.add(12));
        System.out.println(tree.add(17));
        System.out.println(tree.add(19));
        System.out.println(tree.add(199));
        System.out.println("Обшее количество нод в дереве = " + tree.counter());
    }
}
