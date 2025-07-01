class Node11 {
    int data;
    Node11 next;

    public Node11(int data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    Node11 head = null;
    Node11 tail = null;

    // Method to add a node
    public void insert(int data) {
        Node11 newNode = new Node11(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head; // Circular link
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Point last node to head
        }
    }

    // Method to display the circular linked list
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node11 current = head;
        System.out.println("Circular Linked List:");
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head); // Stop when loop returns to head

        System.out.println("(back to head)");
    }

    public void delete(int value) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node11 current = head;
        Node11 previous = tail;
        boolean found = false;

        do {
            if (current.data == value) {
                found = true;

                // Only one node in the list
                if (head == tail) {
                    head = null;
                    tail = null;
                }
                // Deleting the head
                else if (current == head) {
                    head = head.next;
                    tail.next = head;
                }
                // Deleting other nodes
                else {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                    }
                }
                System.out.println("Deleted: " + value);
                return;
            }

            previous = current;
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("Value " + value + " not found in the list.");
        }
    }
}

public class Day13_021 {
    public static void main(String[] args) {
        CircularLinkedList clist = new CircularLinkedList();

        clist.insert(10);
        clist.insert(20);
        clist.insert(30);
        clist.insert(40);

        clist.display(); // Output: 10 -> 20 -> 30 -> 40 -> (back to head)

        clist.delete(30);
        clist.display();
    }
}
