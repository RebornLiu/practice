public class PalindromeList {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                break;
            }
            fast = fast.next;
        }


        ListNode nHead = reverseList(slow);
        return eqList(head, nHead);
    }


    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode next = head.next;
        ListNode nHead = reverseList(next);
        next.next = head;
        head.next = null;

        return nHead;
    }

    public boolean eqList(ListNode list1, ListNode list2) {
        while (list1 != null && list2 != null) {
            if (list1.val != list2.val) {
                return false;
            }

            list1 = list1.next;
            list2 = list2.next;
        }

        return true;
    }
}
