class Node6{
    Node6 next;
    int data;
    Node6(int x)
    {
        data=x;
        next=null;
    }
}
class LList{
    Node6 head;
    void insert(int x){
        Node6 l = new Node6(x);
        if (head == null) {
            head = l;


        }
        else {
            Node6 current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = l;

        }

    }
    public void display(){

        Node6 current= head;

        do{
            System.out.print(current.data  + " -> ");
            current=current.next;
        }while(current!=null);
        System.out.println("null");
    }

}
public class Day14_001 {
    public static void main(String[] args) {


        LList obj = new LList();

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
