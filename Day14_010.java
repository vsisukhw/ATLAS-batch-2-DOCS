class Nodes {
    int data;
    Nodes next;

    Nodes(int data) {
        this.data = data;
        this.next = null;
    }
}

class MyQueue {
    Nodes front, rear;
    int size = 0;
    int capacity = 5;

    boolean isEmpty() {
        return size == 0;
    }


    boolean isFull() {
        return size == capacity;
    }


    void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue " + value );
            return;
        }

        Nodes newNode = new Nodes(value);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }


    void dequeue() {
        if (isEmpty()) {
            System.out.println("\nQueue is empty.");
            return;
        }
        System.out.println("Deleted...");

        front = front.next;
        size--;

        if (front == null) {
            rear = null;
        }


    }


    void peek() {
        if (isEmpty()) {
            System.out.println("\nQueue is empty.");

        }
        System.out.println(front.data);

    }


    void display() {
        if (isEmpty()) {
            System.out.println("\nQueue is empty.");
            return;
        }

        Nodes current = front;
        System.out.print("\nQueue: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }



}
public class Day14_010 {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);

        q.display();

        System.out.println("\nIs Empty? " + q.isEmpty());
        System.out.println("Is Full? " + q.isFull()+"\n");

        q.dequeue();
        q.dequeue();

        q.display();

        System.out.println("\nIs Empty? " + q.isEmpty());
        System.out.println("Is Full? " + q.isFull()+"\n");

        q.dequeue();
        q.dequeue();
        q.dequeue();

        q.display();

        System.out.println("\nIs Empty? " + q.isEmpty());
        System.out.println("Is Full? " + q.isFull()+"\n");
    }
}
