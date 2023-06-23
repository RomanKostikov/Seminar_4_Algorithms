package classwork;
public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(7);
        tree.add(6);
        tree.add(6);
        System.out.println(tree.contains(6));
    }
}
