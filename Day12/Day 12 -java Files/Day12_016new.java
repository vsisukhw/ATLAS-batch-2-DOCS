import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Day12_016new {
    public static void main(String[] args) {
        // Step 1: Create a regular HashMap
        HashMap<String, String> map = new HashMap<>();


        map.put(null, "First Null Key");
        map.put(null, "Second Null");
        map.put("101", "Meera");
        map.put("102", "Anitha");
        map.put(null, "Updated Null Value");


        Map<String, String> syncMap = Collections.synchronizedMap(map);

        //  Synchronized block 
        synchronized (syncMap) {
            for (String key : syncMap.keySet()) {
                System.out.println("Key: " + key + ", Value: " + syncMap.get(key));
            }
        }
    }}