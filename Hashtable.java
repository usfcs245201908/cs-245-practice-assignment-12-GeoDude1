import java.util.*;
public class Hashtable {
    int size;
    ArrayList<HashNode>buckets;  
    int numbuckets;

    public class HashNode {
        String key;
        String value;
        HashNode next;

        public HashNode(String key, String value) {
            this.key=key;
            this.value=value;
            next=null;
        }
    }
    public Hashtable(){
        buckets = new ArrayList<HashNode>();
        numbuckets=2028;
        size=0;
        for (int i=0; i < numbuckets; i++){
            buckets.add(null);
        }
    }

    //this puts or adds a value to the hashtable 
    public void put (String key, String value) {
        int index = getHash(key);
        HashNode head = buckets.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = buckets.get(index);
        HashNode newNode = new HashNode(key, value);
        newNode.next=head;
        buckets.set(index, newNode);
        if ((1.0*size)/ numbuckets >= 0.5) {
            ArrayList<HashNode> temp = buckets;
            numbuckets = 2 * numbuckets; 
            buckets = new ArrayList<HashNode>(numbuckets); 
            size = 0; 
            for (int i = 0; i < numbuckets; i++) {
                buckets.add(null); 
            }
            for (HashNode headReplace : temp) { 
                while (headReplace != null) { 
                    put(headReplace.key, headReplace.value); 
                    headReplace = headReplace.next; 
                } 
            } 
        }
    }

    // this removes from the hashtable and it returns the key of that value
    public String remove(String key) throws Exception { 
        int index = getHash(key);
        HashNode head = buckets.get(index);
        HashNode prev = null;
        while (head != null && head.key.equals(key)==false) {
            prev = head;
            head = head.next;
        }
        if (head == null) {
            throw new Exception();
        }
        size--;
        if (prev != null) {
            prev.next = head.next;
        }
        else {
            buckets.set(index, head.next);
        }
        return head.value;
    }

    // returns the key as a string if it is found if not returns null
    public String get(String key) {
        int index = getHash(key);
        HashNode head = buckets.get(index);
        while(head!=null) {
            if((head.key).equals(key)) {
                return head.value;
            }
            head=head.next;
        }
        return null;
    }

    private int getHash(String key){
        return (Math.abs(key.hashCode()) % numbuckets);
    }

    // this looks for a certain key, if it does not have it then it returns false
    public boolean containsKey(String key) {
        int index = getHash(key);
        HashNode head = buckets.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}