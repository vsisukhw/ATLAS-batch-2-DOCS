class Customer{
    void accept()
    {
        System.out.println("accept customer called");
    }
    void display()
    {
        System.out.println("display customer called");
    }
}
public class Task008 {
    public static void main(String[] args) {
        Customer obj =new Customer();
        obj.accept();
        obj.display();
    }

}
