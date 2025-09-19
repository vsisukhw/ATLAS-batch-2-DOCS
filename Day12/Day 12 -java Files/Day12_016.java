import java.util.*;

public class Day12_016 {
    public static void main(String[] args) {
        HashMap<String, Integer> ht = new LinkedHashMap<>();
        ht.put("Anitha", 101);
        ht.put("Kavitha", 102);
        ht.put("Meera", 103);
        ht.put(null, 106);
        ht.put("Meera", 109);
        ht.put(null, 100);
     //  Map<String, Integer> syncMap = Collections.synchronizedMap(ht);
        // use  get method of ht
        for (Map.Entry<String, Integer> e : ht.entrySet())
            System.out.println(e.getKey() + " " + e.getValue());
    }
}
