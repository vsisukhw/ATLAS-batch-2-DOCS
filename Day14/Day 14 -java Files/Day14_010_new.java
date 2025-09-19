import java.util.*;

class Queue {
    int[] queue;
    int rear, front, size, capacity;
    public Queue(int cap)
    {
        rear=-1;
        front=-1;
        size=0;
        capacity=cap;
        queue= new int[capacity];
    }
    public boolean isempty()
    {return size==0;}
    public boolean isfull()
    {return size==capacity; }
    public void enqueue(int a)
    {
        if (isfull()) {
            System.out.println(" No Capacity ,Cant add elements..." +a);
        }
        else if(isempty()){
            front=0; rear=0; size=1;
        }
        else{
            rear=(rear+1);
            size++;
        }
        queue[rear]=a;
    }
    public void dequeue()
    {
        if(isempty()){
            System.out.println(" Empty, Cant delete elements...");
        }
        else{
            front=(front+1);
            size--;
        }
        System.out.println("Deleted......");
    }
    public void display()
    {
        for(int i=front;i<=rear;i++)
        {
            System.out.print( queue[i] + " -> ");
        }
        System.out.println("null");
    }
}
    public class Day14_010_new {

        public static void main(String[] args) {

            Queue cq = new Queue(5);
            cq.enqueue(1);
            cq.enqueue(2);
            cq.enqueue(22);
            cq.enqueue(3);
            cq.enqueue(33);
            cq.enqueue(4);
            cq.enqueue(44);
            cq.display();
            System.out.println("Is Empty? " + cq.isempty()); // false
            System.out.println("Is Full? " + cq.isfull());   // true
            cq.dequeue();
            cq.dequeue();
            cq.display();
            System.out.println("Is Empty? " + cq.isempty()); // false
            System.out.println("Is Full? " + cq.isfull());   //
        }

    }


