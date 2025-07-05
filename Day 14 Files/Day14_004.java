class Node666{
    Node666 next;
    int data;
    Node666(int x)
    {
        data=x;
        next=null;
    }
}
class LList11{
    Node666 head;
    void insert(int x){
        Node666 l = new Node666(x);
        if (head == null) {
            head = l;
            l.next=head;

        }
        else {
            Node666 current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = l;
            l.next=head;
        }

    }
    public void display(){

        Node666 current= head;

        do{
            System.out.print(current.data  + " -> ");
            current=current.next;
        }while(current!=head);
        System.out.println(head.data);
    }
    public void remove(int value) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        // Case 1: Head node is to be deleted
        if (head.data == value) {
            // Only one node
            if (head.next == head) {
                head = null;
                return;
            }

            // More than one node
            Node666 current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = head.next;
            head = head.next;
            return;
        }

        // Case 2: Non-head node
        Node666 prev = head;
        Node666 curr = head.next;

        while (curr != head) {
            if (curr.data == value) {
                prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;
        }

        // Value not found
        System.out.println("Value " + value + " not found in the list.");
    }


}
public class Day14_004 {
    public static void main(String[] args) {


        LList11 obj = new LList11();

        obj.insert(1);
        obj.insert(2);
        obj.insert(3);
        obj.insert(4);
        obj.insert(5);
        obj.insert(6);
        obj.insert(7);
        obj.display();
        obj.remove(4);
        obj.display();

    }
}
