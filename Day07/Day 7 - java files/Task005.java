import java.awt.geom.RectangularShape;

public class Task005 {
    public static int add(int a, int b)
    {
        int res=a+b;
        return res;
    }
    public static int subtract(int a, int b)
    {
        int res=a-b;
        return res;
    }
    public static int multiply(int a, int b)
    {
        int res=a*b;
        return res;
    }
    public static int divide(int a, int b)
    {
        int res=a/b;
        return res;
    }

    public static void main(String[] args)
    {
        int result1,result2,result3,result4;
        result1=add(5,6);
        result2=subtract(4,1);
        result3=multiply(4,9);
        result4=divide(8,2);
        System.out.println("OPERATIONS \nADDING :"+ result1+"\nSUBSTARCTION : "+result2+"\nMULTIPLY : "+result3+"\nDIVIDE : "+result4);

    }
}
