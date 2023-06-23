package classwork;

public class HashTable <K, V> {
    private static final int INIT_BASKET_COUNT = 25;
    private Basket[] baskets;

    public HashTable(int initsize){
        baskets = (Basket[]) new Object[initsize];
    }

    public HashTable(){
        this(INIT_BASKET_COUNT);
    }

    private int calculateBasketIndex(K key){
        return key.hashCode() % baskets.length;
        // 123423   % 25 = 23
    }

    public boolean put(K key, V value){
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index];
        if (basket == null)
        {
            basket = new Basket();
            baskets[index] = basket;
        }
        Entity entity = new Entity(key, value);
        return basket.add(entity);
    }

    public boolean remove(K key){
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index];
        return basket.remove(key);
    }

    public V get(K key){
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index];
        if (basket != null){
            return basket.get(key);
        }
        return null;
    }


    private class Entity{
        private K key;
        private V value;

        public Entity(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    private class Basket{
        private Node head;

        public V get(K key){
            Node node = head;
            while (node != null){
                if (node.pair.key.equals(key)){
                    return node.pair.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean remove(K key){
            if (head != null){
                if (head.pair.key.equals(key)){
                    head = head.next;
                }
                else{
                    Node node = head;
                    while(node.next != null){
                        if (node.next.pair.key.equals(key)){
                            node.next = node.next.next;
                            return true;
                        }
                        node = node.next;
                    }
                }
            }
            return false;
        }

        public boolean add(Entity entity){
            Node node = new Node();
            node.pair = entity;
            if (head != null){
                Node current = head;
                while (true){
                    if (current.pair.key.equals(entity.key)){
                        return false;
                    }
                    if (current.next == null){
                        current.next = node;
                        return true;
                    }else{
                        current = current.next;
                    }
                }
            }
            else{
                head = node;
                return true;
            }
        }

        private class Node{
            private Entity pair;
            private Node next;
        }
    }
}
// 1:"one" 2:"dfg" 3:"dhg" 4:"gjh"