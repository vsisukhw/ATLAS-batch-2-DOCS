package Enumerations;
enum color{
    red, blue, green, yellow
}

enum Weekdays{
    Sunday , Monday , Tuesday
}
public class Task016 {

    public static void main(String[] args) {
        color c1 = color.yellow;
        System.out.println(c1);

        Weekdays w1 = Weekdays.Tuesday;
        System.out.println(w1);
    }
}

