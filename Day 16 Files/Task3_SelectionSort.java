public class Task3_SelectionSort
{
    void sort(int[] arr, int n)
    {
        for(int i=n-1;i>=0;i--)
        {
                int max = i;
                for(int j=0;j<i;j++)
                {
                    if(arr[j]>arr[max])
                    {
                        max=j;
                    }
                }
                swap(arr,max,i);
        }

        for(int a: arr) {
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
        Task3_SelectionSort obj = new Task3_SelectionSort();
        obj.sort(a,n);
    }
}
