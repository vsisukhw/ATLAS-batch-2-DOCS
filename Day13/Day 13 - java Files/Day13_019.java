import java.util.HashMap;

public class Day13_019 {
    public static void main(String[] args) {
        // Initial capacity = 10
//        ls

        HashMap<Integer, String> map = new HashMap<>(10,0.5f);

        // After inserting the 10th item, resizing should occur internally
        for (int i = 1; i <= 50; i++) {
            map.put(i, "Value" + i);
            System.out.println("Key: " + i + ", size: " + map.size());

            // Custom logic to estimate if resize may have occurred (we can't access capacity directly)
            if (i%10==0) {
                System.out.println(" Likely resized at this point (Java doubled capacity internally)");
            }
        }
    }
}