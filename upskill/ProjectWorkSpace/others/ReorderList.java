

public class ReorderList {

    public static void main(String[] args) {
        // Example usage:
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        //head.next.next.next.next = new ListNode(5);

        SolutionReorderList solution = new SolutionReorderList();
        solution.reorderList(head);

        // Print reordered list
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
class SolutionReorderList {
    public void reorderList(ListNode head) {
        if (head == null) return;

        // Step 1: Find the middle of the list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the list
        ListNode second = slow.next;
        slow.next = null;
        ListNode node = null;

        while (second != null) {
            ListNode temp = second.next;
            second.next = node;
            node = second;
            second = temp;
        }

        // Step 3: Merge the two halves
        ListNode first = head;
        second = node;

        while (second != null) {
            ListNode temp1 = first.next, temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;
        }        
    }
}