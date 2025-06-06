import java.util.Scanner;

public class Task012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count=0;
        System.out.println("Enter login ID = ");
        String id = sc.next();
        System.out.println("Enter Password = ");
        int pwd= sc.nextInt();
        while(id!="Sukhwinder" && pwd!=1234)
        {
            System.out.println("you have logged in "+ ++count +" times");
            System.out.println("Enter login ID = ");
            id = sc.next();
            System.out.println("Enter Password = ");
            pwd= sc.nextInt();
        }
        System.out.println("You have entered in portal");
    }
}
