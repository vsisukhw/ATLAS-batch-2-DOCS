
public class Day14_011 {
    static int factorial(int n)
    {
        if (n==1)
        {
            return 1;
        }
        else
            return (n*factorial(n-1));
    }

    static int fibonacci(int n)
    {
        if(n<2)
        {
            return n;
        }
        else
            return fibonacci(n-1)+fibonacci(n-2);
    }
    static int search(int[] a, int target, int index){

        if (a[index]==target)
        {
            return index;
        }
        else
        {
            return search(a,target,++index);
        }

    }
    static int countDigits(int a)
    {
            if(a/10==0)
            {
                return 1;
            }
            else
            {
                return countDigits(a/10)+1;
            }

    }
    static int sumDigits(int a)
    {   int sum=0;
        if(a==0)
        {

            return 0;
        }
        else
        {


            return (a%10)+sumDigits(a/10);
        }

    }
//    static String reverseString(String st)
//    {
//        if(st.charAt(-1))
//    }

    public static void main(String[] args) {
        System.out.println(factorial(10));
        System.out.println(fibonacci(7));

        int[] numbers = {1,2,3,4,5,6,7,8,9};
        for(int i=0;i<numbers.length;i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println("\nSearched element is at Index = " + search(numbers,6,0));

        System.out.println("\nDigits Count = "+ countDigits(1057));
        System.out.println("Sum of digits = " + sumDigits(542));
    }
}
