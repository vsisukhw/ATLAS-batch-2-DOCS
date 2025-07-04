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
        if (head == null)
            head = l;
        else {
            Node66 current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = l;
        }

    }
    public void display(){

        Node66 current= head;
        while(current!=null)
        {
            System.out.print(current.data  + " -> ");
            current=current.next;
        }
        System.out.println("null");
    }

}

public class Day14_003 {
}
