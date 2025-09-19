import java.util.HashMap;

public class Day13_018 {
    public static void main(String[] args) {
        // Initial capacity = 10
        HashMap<Integer, String> map = new HashMap<>(10);


        // After inserting the 10th item, resizing should occur internally
        for (int i = 1; i <= 5; i++)
        {
            map.put(i, "Value" + i);
        }
        HashMap<Integer, String> map2 = new HashMap<Integer, String>( map);
        System.out.println(map);
        System.out.println(map2);
    }
}