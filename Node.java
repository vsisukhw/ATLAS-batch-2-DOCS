public class Node {
    Node next;
    int data;
    public Node(int a)
    {
        data=a;
        next=null;
    }
}
class Linkedstack{
    Node head;
    public void insertfront(int b)
    {
        Node newnode= new Node(b);
        if (head==null)
        {
            head=newnode;
        }
        else
        {
            newnode.next=head;
            head=newnode;
        }
    }
    public void insertend(int b)
    {
        Node newnode= new Node(b);
        if (head==null)
        {
            head=newnode;
        }
        else
        {
            Node current = head;
            while(current.next!=null)
            {
                current=current.next;
            }
            current.next=newnode;
        }
    }
    public Node removefront()
    {
        Node delnode=head;
        head=head.next;
        return delnode;
    }
    public Node removeend()
    {
        Node current=head;
        while(current.next!=null && current.next.next!=null)
        {
            current=current.next;
        }
        Node c= current.next;
        current.next=null;
        return c;
    }
    public void display()
    {
        Node current=head;
        while(current!=null)
        {
            System.out.print(current.data + " -> ");
            current=current.next;
        }
        System.out.println();
    }

    public void displayEnd() {
        if(head==null){
            return;
        }
        Node current = head;
        while(current.next!=null) {
            current=current.next;
        }
        System.out.println(current.data);
    }

    public static void main(String[] args) {
        Linkedstack node= new Linkedstack();
        node.insertend(1);
        node.insertend(2);
        node.insertend(3);
        node.insertend(4);
        node.insertend(5);
        node.insertend(6);
        node.insertend(7);
        node.display();
        node.insertfront(99);
        node.display();
        node.insertend(84);
        node.display();
        node.removefront();
        node.display();
        node.removeend();
        node.display();
        node.displayEnd();
    }

}
