class OuterClass4 {
    int x = 10;
    static class InnerClass4 {
        static int y = 5;
    }
}
public class Task15 {
    public static void main(String[] args) {
        OuterClass4.InnerClass4 myInner4 = new OuterClass4.InnerClass4();
        System.out.println(myInner4.y);

    }
}
