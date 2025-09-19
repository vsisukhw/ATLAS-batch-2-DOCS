class Node {
    String data;
    Node next,prev;

    public Node(String ch)
    {
        data = ch;
        next = null;
        prev = null;

    }

}
class DoublyLinkedlist {
    Node head;

    public void insert(String ch) {
        Node newNode = new Node(ch);
        if (head == null)
            head = newNode;
        else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void display(){

        Node temp= head;
        while(temp!=null)
        {
            System.out.print(temp.data  + " <-> ");
            temp=temp.next;
        }
        System.out.print("null");
    }

    public void delete(String ch){
        Node temp=head, prev=null;
        if(temp.data==ch) {
            head=temp.next;
            temp.next=null;
            return;
        }
        while(temp!=null)
        {
            if(temp.data==ch)
            {
                prev.next=temp.next;
                return;
            }
            prev=temp;
            temp=temp.next;
        }
    }


}
public class Day13_016 {
    public static void main(String[] args) {
        DoublyLinkedlist tt = new DoublyLinkedlist();

        tt.insert("alice");
        tt.insert("bob");
        tt.insert("sukh");
        tt.insert("singh");
        tt.insert("1");
        tt.insert("2");
        tt.insert("3");
        tt.display();
        System.out.println();
        tt.delete("sukh");
        tt.display();
    }
}
