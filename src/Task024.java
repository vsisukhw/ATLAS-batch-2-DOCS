class Student1{
    public String name;
    Student1(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}
public class Task024{
    public static void main (String[] args){

        // declares an Array and initializing the
        // elements of the array
        Student1[] myStudents = new Student1[]{
                new Student1("Dharma"),new Student1("sanvi"),
                new Student1("Rupa"),new Student1("Ajay")
        };
        // accessing the elements of the specified array
        for(Student1 m:myStudents){
            System.out.println(m);
        }
    }
}


