class Node2 {
    String data;
    Node2 next;

    public Node2(String ch)
    {
        data = ch;
        next = null;

    }

}
class Linkedlist1{
    Node2 head;

    public void insert(String ch) {
        Node2 newNode = new Node2(ch);
        if (head == null)
            head = newNode;
        else {
            Node2 temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void display(){

        Node2 temp= head;
        while(temp!=null)
        {
            System.out.print(temp.data  + " -> ");
            temp=temp.next;
        }
        System.out.print(" null");
    }

    public void delete(String ch){
        Node2 temp=head;
        if(temp.data==ch) {
            head=temp.next;
            temp.next=null;
            return;
        }
        while(temp!=null)
        {
            if(temp.next!=null && temp.next.data==ch)
            {
                temp.next=temp.next.next;
                return;
            }
            temp=temp.next;
        }
    }




}
public class Day13_003{

public void main() {
    Linkedlist1 tt = new Linkedlist1();

    tt.insert("alice");
    tt.insert("bob");
    tt.insert("sukhwinder");
    tt.insert("singh");
    tt.display();


    tt.delete("sukhwinder");
    System.out.println(" ");
    tt.display();


}}
