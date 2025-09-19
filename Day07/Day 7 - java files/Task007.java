import java.util.Scanner;

public class Task007 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your Id  -  ");
        String name=sc.next();
        System.out.println("What is your password  - ");
        int pwd= sc.nextInt();
        System.out.println("Hello, your login id is "+ name + " \n Your password is " + pwd);
    }
}
