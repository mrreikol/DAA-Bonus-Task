package hash;

public class HashTable {
    private HashNode[] buckets;
    private int capacity;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public HashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.buckets = new HashNode[capacity];
        this.size = 0;
    }

    private int hash(int key) {
        return Math.abs(Integer.hashCode(key)) % capacity;
    }

    public void insert(int key) {
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int bucketIdx = hash(key);
        HashNode current = buckets[bucketIdx];

        while (current != null) {
            if (current.key == key) {
                return;
            }
            current = current.next;
        }

        HashNode newNode = new HashNode(key);
        newNode.next = buckets[bucketIdx];
        buckets[bucketIdx] = newNode;
        size++;
    }

    public boolean search(int key) {
        int bucketIdx = hash(key);
        HashNode current = buckets[bucketIdx];
        while (current != null) {
            if (current.key == key) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean delete(int key) {
        int bucketIdx = hash(key);
        HashNode current = buckets[bucketIdx];
        HashNode prev = null;

        while (current != null) {
            if (current.key == key) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    buckets[bucketIdx] = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    private void resize() {
        HashNode[] oldBuckets = buckets;
        capacity *= 2;
        buckets = new HashNode[capacity];
        size = 0;

        for (HashNode head : oldBuckets) {
            while (head != null) {
                insert(head.key);
                head = head.next;
            }
        }
    }
}