abstract class Personn {
    private String name;
    private int age;
    public Personn(String name, int age) {
        this.name = name; this.age = age;
    }
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

class Employeee extends Personn {
    private String empId;
    private double salary;
    public Employeee(String name, int age, String empId, double salary) {
        super(name, age);
        this.empId = empId; this.salary = salary;
    }
    public String toString() {
        return super.toString() + ", EmpID: " + empId + ", Salary: " + salary;
    }
}

class Manager extends Employeee {
    private String dept;
    private int teamSize;
    public Manager(String name, int age, String empId, double salary, String dept, int teamSize) {
        super(name, age, empId, salary);
        this.dept = dept; this.teamSize = teamSize;
    }
    public String toString() {
        return super.toString() + ", Dept: " + dept + ", Team Size: " + teamSize;
    }
}

public class Task16 {
    public static void main(String[] args) {
        Manager m = new Manager("Alice", 35, "E101", 75000, "HR", 5);
        System.out.println(m);
    }
}
