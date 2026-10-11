package hash;

public class HashNode {
    public int key;
    public HashNode next;

    public HashNode(int key) {
        this.key = key;
        this.next = null;
    }
}