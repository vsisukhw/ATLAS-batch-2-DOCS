import java.util.*;

public class Day14_006 {
    public static void main(String[] args) {
        Stack obj = new Stack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.push('f');
        obj.push(5);
        obj.push("hello");

        System.out.println(obj);
        int count=1;
        for(Object o:obj)
        {

            if(o.equals("hello"))
            {
                System.out.println("using for-each loop, the position is : " + count);

            }

            count++;

        }

        System.out.println("Using search method, the position is : "+obj.search("hello"));
        System.out.println(obj.search(3));


    }

}


