class Node66{
    Node66 next;
    int data;
    Node66(int x)
    {
        data=x;
        next=null;
    }
}
class LList1{
    Node66 head;
    void insert(int x){
        Node66 l = new Node66(x);
        if (head == null) {
            head = l;
            l.next=head;

        }
        else {
            Node66 current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = l;
            l.next=head;
        }

    }
    public void display(){

        Node66 current= head;

        do{
            System.out.print(current.data  + " -> ");
            current=current.next;
        }while(current!=head);
        System.out.println(head.data);
    }

}
public class Day14_003 {
    public static void main(String[] args) {


        LList1 obj = new LList1();

        obj.insert(1);
        obj.insert(2);
        obj.insert(3);
        obj.insert(4);
        obj.insert(5);
        obj.insert(6);
        obj.insert(7);
        obj.display();

    }
}
