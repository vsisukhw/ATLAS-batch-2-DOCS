

public class Day11_010 {
    public static void main(String[] args) {



        char ch=' ';
        String  s = "hello, this is Sukhwinder";
        System.out.println(s.length());
        for(int c= s.length();c>0;c-- )
        {
            System.out.print(s.charAt(c-1));
        }
    }
}
