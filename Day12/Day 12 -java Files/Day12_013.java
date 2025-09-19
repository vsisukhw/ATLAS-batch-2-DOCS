import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Day12_013 {
    public static void main(String[] args) {
        HashMap<String, Integer> ht = new HashMap<>();
        ht.put("Anitha", 101);
        ht.put("Kavitha", 102);
        ht.put("Meera", 103);
        // use  get method of ht
        for (Map.Entry<String, Integer> e : ht.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
    }
}

