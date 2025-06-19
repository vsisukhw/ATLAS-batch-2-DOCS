class OuterClass2 {
    int x = 10;
     static class InnerClass2 {
        int y = 5;
    }
}

public class Task13 {
    public static void main(String[] args) {
        OuterClass2 myOuter2 = new OuterClass2();
        OuterClass2.InnerClass2 myInner2 = new OuterClass2.InnerClass2();
        System.out.println(myInner2.y + myOuter2.x);
    }
}