public class Task036 {

    void add(int x, float y){
        System.out.println("x = "+x+"  y = "+y);
    }
    void add(float x, int y){
        System.out.println("x = "+x+"  y = "+y);
    }

    public void main(String[] args) {
        add(10.50f, 60);
        add(100, 80.80f);

    }

}
