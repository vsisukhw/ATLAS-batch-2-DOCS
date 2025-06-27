import java.util.Scanner;

public class Day12_008 {


    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the string = ");
        String s = sc.nextLine();
        int len = s.length();
        for(int i=len-1;i>=0;i--)
        {
            System.out.println(s.charAt(i));
        }

    }

}
