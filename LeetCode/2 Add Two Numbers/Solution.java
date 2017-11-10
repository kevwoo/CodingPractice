/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p3 = new ListNode(0);      // dummy head
        ListNode curr = p3;
        int sum;
        int x = 0, y = 0;
        while (p1 != null || p2 != null){
            x = (p1 == null) ? 0 : p1.val;
            y = (p2 == null) ? 0 : p2.val;
            sum = x + y + carry;
            curr.next = new ListNode(sum % 10);
            carry = sum / 10;
            p1 = (p1 == null)? p1 : p1.next;
            p2 = (p2 == null)? p2 : p2.next;
            curr = curr.next;
        }   // end while loop
        if (carry > 0)
            curr.next = new ListNode(carry);

        return p3.next;

    }
}
