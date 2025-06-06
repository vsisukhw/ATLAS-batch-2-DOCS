class Person1 {
    private String name1;

    // Getter
    public String getName1() {
        return name1;
    }

    // Setter
    public void setName1(String newName) {
        this.name1 = newName;
    }
}

public class Task018{
    public static void main(String[] args) {
            Person1 myObj1 = new Person1();
            myObj1.setName1("John");
            System.out.println(myObj1.getName1());

        }
}