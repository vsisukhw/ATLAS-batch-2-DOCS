public class Task004 {
    static void swap(int a, int b)
    {
        int var;
        var=a;
        a=b;
        b=var;
        System.out.println("After Swapping , the values are " +a +" and "+b);
    }

    public static void main(String[] args) {
        swap(2,5);
    }
}
