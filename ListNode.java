public class ListNode {
    int val;
      ListNode next;
      ListNode()
      {

      }
    ListNode(int val)
    {
        this.val = val;
    }
    ListNode(int val, ListNode next)
    {
        this.val = val; this.next = next;
    }
  }

class Solution {
    public ListNode swapPairs(ListNode head)
    {
        ListNode node=new ListNode(head.val,head);
        if(node==null)
        {
            System.out.println(" no node");
        }
        else
        {
            while(node.next!=null)
            {
                ListNode current=node;
                node=node.next;
                node.next=current;
                node=node.next;

            }
        }

        while(node.next!=null)
        {


            node=node.next;

        }
        return node;
    }

}


