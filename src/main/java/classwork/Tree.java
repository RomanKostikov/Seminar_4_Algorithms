package classwork;

/*
Tree
 */
public class Tree <V extends Comparable<V>>{
    private Node root;

    public boolean contains(V value){
        Node node = root;
        while (node != null){
            if (node.value.equals(value)){
                return true;
            }
            if (node.value.compareTo(value) > 0){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return false;
    }

    public void add(V value){
        Node node = root;
        Node newNode = new Node();
        newNode.value = value;
        if (root == null){
            root = newNode;
        }
        else{
            if (node.value.compareTo(value)> 0){
                node.left = newNode;
            }
            else if (node.value.compareTo(value)< 0) {
                node.right = newNode;
            }
            else {
                System.out.println("РўР°РєРѕР№ СЌР»РµРјРµРЅС‚ СѓР¶Рµ РµСЃС‚СЊ");
            }
        }

    }

    private class Node{
        private V value;
        private Node left;
        private Node right;
    }

}

//        *
// * * *     * * *