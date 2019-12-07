public class Hashtable {
    int sizeOfHashTable;
    Node[] elements;

    private class Node {
        String key;
        String value;
        Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.next = null;
        }
    }

    Hashtable() {
        this.sizeOfHashTable = 1000;
        this.elements = new Node[1000];
    }

    Hashtable(int size){
        this.sizeOfHashTable = size;
        this.elements = new Node[size];
    }

    // this looks for a certain key, if it does not have it then it returns false
    public boolean containsKey(String key) {
        int hash = hash(key);
        if(elements[hash] != null) {
            return true;
        }

        return false;
    }

    // returns the key as a string if it is found if not returns null
    public String get(String key) {
        int hash = hash(key);
        if(elements[hash] != null) {
            Node temp = elements[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    return temp.value;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    //this puts or adds a value to the hashtable 
    public void put(String key, String value) {
        Node hashElement = new Node(key, value);
        int hash = hash(key);
        if(elements[hash] == null) {
            elements[hash] = hashElement;
        } else {
            hashElement.next = elements[hash];
            elements[hash] = hashElement;
        }
    }

    // this removes from the hashtable and it returns the key of that value
    public String remove(String key){
        int hash = hash(key);
        if(elements[hash] != null) {
            Node temp = elements[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    elements[hash] = null;
                    return temp.value;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    private int hash(String key) {
        int hashCode = Math.abs(key.hashCode());
        return hashCode % sizeOfHashTable;
    }

}