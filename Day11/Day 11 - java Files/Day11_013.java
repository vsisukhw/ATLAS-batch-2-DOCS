import java.util.ArrayList;
import java.util.List;

public class Day11_013 {
    public static void main(String[] args) {
        List<String> fullName = new ArrayList();
        fullName.add("Sukhwinder,Singh");
        fullName.add("Sukhwinder1,Singh1");
        fullName.add("Sukhwinder2,Singh2");
        fullName.add("Sukhwinder3,Singh3");
        fullName.add("Sukhwinder4,Singh4");
        fullName.add("Sukhwinder5,Singh5");
        for(String name : fullName)
        {
            System.out.println(name);
        }


    }
}
