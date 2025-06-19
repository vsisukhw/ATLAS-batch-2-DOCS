class OuterClass3{
    int x = 50;
    class InnerClass3{
        public int innerMethod()
        {
            return x;
        }
    }
}
public class Task14 {
    public static void main(String[] args) {
        OuterClass3 myOuter3 = new OuterClass3();
        OuterClass3.InnerClass3 myInner3 = myOuter3.new InnerClass3();
        System.out.println(myInner3.innerMethod());
    }
}
