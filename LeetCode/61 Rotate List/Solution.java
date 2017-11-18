/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        
        ListNode ptr = head;
        int length = 1;
        while (ptr.next != null){
            ptr = ptr.next;
            length++;
        }
        
        if (k >= length)
            k %= length;
        
        ptr.next = head;
        ListNode cutNode = head;
        int count = length - k;
        int i = 1;
        while (i < count){
            cutNode = cutNode.next;
            i++;
        }
        
        head = cutNode.next;
        cutNode.next = null;
        
        return head;
    }
}
