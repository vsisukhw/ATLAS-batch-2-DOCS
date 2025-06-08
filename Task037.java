class Employee{
    private int pwd;
    protected int Salary;
    public int empid;
}
class Hr extends Employee {
    void details()
    {
        //super.pwd = 1254;
        super.Salary = 50000;
        super.empid = 10001;
        System.out.println(empid);
        System.out.println(Salary);
        //System.out.println(pwd);
    }
}
public class Task037{
    public static void main(String[] args) {
        Hr obj =new Hr();
//        System.out.println(obj.empid);
//        System.out.println(obj.Salary);
//        System.out.println(obj.pwd);
        obj.details();
    }
}


