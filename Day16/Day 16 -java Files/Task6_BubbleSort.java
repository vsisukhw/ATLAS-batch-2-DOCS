public class Task6_BubbleSort {
    void sort(int[] arr, int n)
    {
        for(int i=n-1;i>=0;i--)
        {

            for(int j=0;j<i;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    swap(arr,j,j+1);
                }
            }

        }

        for(int a: arr)
        {
            System.out.print(a +" ");
        }
    }

    void swap(int[] arr,int a, int b)
    {
        int swap= arr[a];
        arr[a]=arr[b];
        arr[b]=swap;
    }

public static void main(String[] args) {
    int[] a= {1,3,2,4,6,5,7};
    int n= a.length;
    Task6_BubbleSort obj = new Task6_BubbleSort();
    obj.sort(a,n);
}
}