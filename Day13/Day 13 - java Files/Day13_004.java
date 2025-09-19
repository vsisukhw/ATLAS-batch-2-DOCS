class Exception1 extends Exception {
    public Exception1(String message) {
        super(message);
    }
}

class Node3 {
    Object data;
    Node3 next;

    public Node3(Object ch) {
        data = ch;
        next = null;
    }
}

class Linkedlist2 {
    Node3 head;
    int count1 = 0;

    public void insert(Object ch) throws Exception1 {
        if (count1 >= 4) {
            throw new Exception1("Error: Index out of bounds");
        }

        Node3 newNode = new Node3(ch);
        if (head == null)
            head = newNode;
        else {
            Node3 temp1 = head;
            while (temp1.next != null) {
                temp1 = temp1.next;
            }
            temp1.next = newNode;
        }
        count1++;
    }

    public void display() {
        Node3 temp1 = head;
        while (temp1 != null) {
            System.out.print(temp1.data + " -> ");
            temp1 = temp1.next;
        }
        System.out.print(" null");
    }

    public void delete(String ch){
        Node3 temp=head;
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

    public void size() {
        int count = 0;
        Node3 temp1 = head;
        while (temp1 != null) {
            count++;
            temp1 = temp1.next;
        }
        System.out.println(" ");
        System.out.println("Size = " + count);
    }
}

public class Day13_004 {
    public static void main(String[] args) {
        Linkedlist2 tt = new Linkedlist2();

        try {
            tt.insert("alice");
            tt.insert(1);
            tt.insert("sukhwinder");
            tt.insert('c');
            tt.insert("hello");    // This will throw Exception1


        tt.display();
        tt.size();

        System.out.println("\nAfter deleting 'sukhwinder':");
        tt.delete("sukhwinder");
        tt.display();
        tt.size();} catch (Exception1 e) {
            System.out.println("Error: Index out of bounds");
        }
    }
}
