public class Task038 {
    public static void main(String [] args) {
        /* Following is not allowed and would raise error */
        Employee1 e1 = new derived("George W.", "Houston, TX", 43);
        System.out.println("\n Call mailCheck using Employee reference--");
       e1.mailCheck();
    }
}
abstract class Employee1 {
    private String name;
    private String address;
    private int number;
   Employee1(String name, String address, int number) {
        System.out.println("Constructing an Employee");
        this.name = name;
        this.address = address;
        this.number = number;
    }
    public double computePay() {
        System.out.println("Inside Employee computePay");
        return 0.0;
    }
    public void mailCheck() {
        System.out.println("Mailing a check to " + this.name + " " + this.address);
    }
    public String toString() {
        return name + " " + address + " " + number;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String newAddress) {
        address = newAddress;
    }
    public int getNumber() {
        return number;
    }
}
class derived extends Employee1 {
    derived(String n, String a, int nu)
    {
         super(n,a,nu);
    }
}

