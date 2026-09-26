import java.util.HashMap;
import java.util.Map;

public class CopyList{


    public static void main(String[] args) {
        // Example usage
                int[][] input = {{1,1},{2,1}};

            Node[] nodes = new Node[input.length];

            // create nodes
            for (int i = 0; i < input.length; i++) {
                nodes[i] = new Node(input[i][0]);
            }

            // set next pointers
            for (int i = 0; i < input.length - 1; i++) {
                nodes[i].next = nodes[i + 1];
            }

            // set random pointers
            for (int i = 0; i < input.length; i++) {
                int randomIndex = input[i][1];
                nodes[i].random = nodes[randomIndex];
            }

            Node head = nodes[0];


        Node copiedHead = copyRandomList(head);
        // Now copiedHead is a deep copy of the original list
    }
    public static Node copyRandomList(Node head) {
        Map<Integer,Node>map=new HashMap<>();

        Node root=new Node(-1);

        Node cur=root;

        Node temp=head;

        while(temp!=null){
            int v=temp.val;
            map.put(v, new Node(v));
            temp=temp.next;
        }

        temp=head;

        while(temp!=null){
            int v=temp.val;
            Node newNode=map.get(v);

            cur.next=newNode;
            Node r=map.get(temp.random.val);
            newNode.random=r;

            cur=cur.next;
            temp=temp.next;

        }

        return root.next;
    }

}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}